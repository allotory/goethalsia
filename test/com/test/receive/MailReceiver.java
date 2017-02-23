package com.test.receive;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

import sun.misc.BASE64Decoder;

public class MailReceiver {

	public static void main(String[] args) {

		MailReceiver receiver = new MailReceiver();
		receiver.setHost("pop3.126.com");
		receiver.setUsername("bauble");
		receiver.setPassword("severus125874693");
		receiver.setAttachPath("C:email");
		try {
			receiver.reveiveMail();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void reveiveMail() throws Exception {

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		Store store = session.getStore("pop3");
		store.connect(getHost(), getUsername(), getPassword());

		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY);
		Message message[] = folder.getMessages();
		System.out.println("Messages's length: " + message.length);

		// FetchProfile profile = new FetchProfile();
		// profile.add(FetchProfile.Item.ENVELOPE);
		// folder.fetch(message, profile);

		for (int i = 0; i < message.length; i++) {

			// message[i].setFlag(Flags.Flag.DELETED,
			// true);//必须先设置：folder.open(Folder.READ_WRITE);
			handleMultipart(message[i]);
		}
		if (folder != null) {
			folder.close(true);
		}
		if (store != null) {
			store.close();
		}
	}

	private void handleMultipart(Message msg) throws Exception {

		String disposition;
		Multipart mp = (Multipart) msg.getContent();
		int mpCount = mp.getCount();
		for (int m = 0; m < mpCount; m++) {
			handle(msg);
			BodyPart part = mp.getBodyPart(m);
			disposition = part.getDisposition();
			if (disposition != null && disposition.equals(Part.ATTACHMENT)) {
				saveAttach(part, getAttachPath());
			} else {
				System.out.println(part.getContent());
			}
		}
	}

	private static void handle(Message msg) throws Exception {

		System.out.println("邮件主题:" + msg.getSubject());
		System.out.println("邮件作者:" + msg.getFrom()[0].toString());
		System.out.println("发送日期:" + msg.getSentDate());
	}

	private static void saveAttach(BodyPart part, String filePath)
			throws Exception {

		String temp = part.getFileName();
		String s = temp.substring(8, temp.indexOf("?="));
		String fileName = base64Decoder(s);
		System.out.println("有附件:" + fileName);

		InputStream in = part.getInputStream();
		FileOutputStream writer = new FileOutputStream(new File(filePath + ""
				+ fileName));
		byte[] content = new byte[255];
		while ((in.read(content)) != -1) {
			writer.write(content);
		}
		writer.close();
		in.close();
	}

	private static String base64Decoder(String s) throws Exception {

		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = decoder.decodeBuffer(s);

		return (new String(b));
	}

	private String host = null;
	private String username = null;
	private String password = null;
	private String attachPath = null;

	public String getAttachPath() {
		return attachPath;
	}

	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
