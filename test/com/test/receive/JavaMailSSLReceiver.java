package com.test.receive;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.util.*;

public class JavaMailSSLReceiver {
	
	
	public static void main(String[] args) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props);
		// 第1个参数为接受协议，第2个参数为邮件接收服务器的地址，第三个参数为pop3协议的端口，最后两个为用户名和密码
		URLName url = new URLName("pop3", "pop.126.com", 110, null,
				"bauble", "severus125874693");
		// 使用SSL加密传输协议,gmail可以使用ssl协议来保证邮件传输的安全，使用SSL的POP3S的默认端口为995。
		// URLName url=new
		// URLName("pop3s","pop.gmail.com",995,null,"masterspring","spring");

		Store store = null;
		Folder inbox = null;
		try {
			store = session.getStore(url);
			// 使用URLName指定Session邮件环境，包括认证信息
			store.connect(); // 连接邮件服务器

			// 从邮件服务器中返回邮箱内的信息
			inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY); // 打开Folder
			FetchProfile fp = new FetchProfile();
			fp.add(FetchProfile.Item.ENVELOPE);

			Message[] msgs = inbox.getMessages();
			inbox.fetch(msgs, fp);

			// 显示邮件信息
			for (int i = 0; i < msgs.length; i++) {
				String from = msgs[i].getFrom()[0].toString();
				InternetAddress id = new InternetAddress(from);
				System.out.println();
				System.out.println("发送者:" + id.getPersonal() + "/"
						+ id.getAddress());

				System.out.println("标题:" + msgs[i].getSubject());
				System.out.println("大小:" + msgs[i].getSize());
				System.out.println("时间:" + msgs[i].getSentDate());
				System.out.println("类型:" + msgs[i].getContentType());
				System.out.println("内容:" + msgs[i].getContent().toString());
				
			}
		} catch (Exception er) {
			System.out.println("发生异常：" + er.toString());
		} finally {
			try {
				inbox.close(false);
				store.close();
			} catch (Exception e) {
			}
		}
	}
	
}
