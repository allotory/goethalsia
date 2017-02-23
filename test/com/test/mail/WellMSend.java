package com.test.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class WellMSend {
	public static void main(String [] args) {
		
		//发件人
		String sender = "a@mymail.com";
		//收件人
		String recipients = "admin@mymail.com";
		//邮件主题
		String subject = "aggggg222222222222";
		//邮件内容
		String content = "<a>xxxxxxxxxxxx222222222xxxxxxxtrt</a>";
		
		//附件
		String attach[] = new String[]{"中文config.xml.txt","JavaMail API详解.txt"};
		//String attach[] = null;
		
		String mailtype = "text/html";
		
		String mailServer = "localhost";

		// 设置邮件传输协议
		// 生成标准的属性文件对象
		Properties props = System.getProperties();
		// 设置要和哪一个服务器进行连接
		props.put("mail.smtp.host", mailServer);
		// 设置smtp端口
		props.put("mail.smtp.port", "25");
		// 发邮件协议
		props.put("mail.transport.protocol", "smtp");
		// 收邮件协议
		props.put("mail.store.protocol", "pop3");

		// 是否smtp认证
		// props.put("mail.smtp.auth", "true");

		// 建立发送邮件的连接
		// 和prop的主机建立连接，同时不做其他连接null
		Session session = Session.getDefaultInstance(props, null);

		// 在Session这个连接中创建发送信息载体
		Message msg = new MimeMessage(session);
		
		// 创建相关的邮件属性
		try {
			// 设置邮件的发件人地址
			msg.setFrom(new InternetAddress(sender));
			
			// 设置邮件的收件人地址
			// To:普通发送，点对点
			// CC：抄送，同时发多个
			// BCC：暗送，匿名发送
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
			
			String cc1 = "b@mymail.com";
			String cc2 = "c@mymail.com";
			
			String bcc1 = "d@mymail.com";
			
			msg.setRecipients(Message.RecipientType.CC, new InternetAddress[]{new InternetAddress(cc1),new InternetAddress(cc2)});
			msg.setRecipients(Message.RecipientType.BCC, new InternetAddress[]{new InternetAddress(bcc1)});
			
			// 设置邮件主题
			msg.setSubject(subject);
			// 设置邮件发送时间
			Date date = new Date();
			SimpleDateFormat simpledf = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String source = simpledf.format(date);
			date = simpledf.parse(source);
			msg.setSentDate(date);

			// 处理附件
			if (attach == null || attach.length < 1) {

				// 附件为空 ，或者附件数组的长度小于1

				// 判断发送的Mime类型
				// Multipart用于传输二进制数据 mp的组成不是一个整体组成，而是以块或部分的组成
				// 使用setText发送的邮件相当一个“明信片”——只有信封，没有信。而Multipart相当于一个“信”，里面有信的body
				// （一个“信”可以有多个body），mbp.setContent是将content设置为type类型
				Multipart mp = new MimeMultipart();
				MimeBodyPart mbp = new MimeBodyPart();
				// 设置邮件发送制定的类型
				// setContent相当与将content设置为type类型
				mbp.setContent(content, mailtype + ";charset=GB18030");
				// 将“信体”添加进“信”里
				mp.addBodyPart(mbp);
				// 将“信”加到“信封”（msg）里
				msg.setContent(mp);

			} else {

				// 包含附件

				// 对邮件的普通内容进行处理
				Multipart mp = new MimeMultipart();

				MimeBodyPart mbpContent = new MimeBodyPart();
				mbpContent.setContent(content, mailtype + ";charset=GB18030");

				for (int i = 0; i < attach.length; i++) {

					// 对邮件中的附件进行处理
					MimeBodyPart mbpAttach = new MimeBodyPart();
					// 建立文件读取数据源
					DataSource fds = new FileDataSource("d:/upload/"+ attach[i] );
					// 获取（读出）整个附件的信息，此时mbp2里存储的是二进制的流所读出来的字节
					DataHandler dh = new DataHandler(fds);
					mbpAttach.setDataHandler(dh);
					// 开始读
					mbpAttach.setFileName(MimeUtility.encodeText(fds.getName(),
							"GB18030", "B"));

					// 添加附件主体
					mp.addBodyPart(mbpAttach);

				}

				// 添加邮件内容主体
				mp.addBodyPart(mbpContent);
				

				// 添加到“信封”中
				msg.setContent(mp);
			}
			
			File f = new File("d:/test1.eml");
			msg.writeTo(new FileOutputStream(f));
			

			// 发送
			Transport.send(msg);

			System.out.print("邮件发送成功！");
			System.out.println(f.length()+144);
			

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
