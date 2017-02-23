package com.test.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * 普通文本邮件发送
 */

public class SendMailCC {

	public static void send() {

		//发件人
		String from = "severus@mymail.com";
		//收件人
		String to = "bauble@mymail.com";
		//邮件主题
		String subject = "第4封测试邮件bcc,CC";
		//邮件内容
		String content = "这里是第4封测试邮件bcc,CC，谢谢";
		
		//确定发送邮件服务器地址   （不包括.com）
		String mailServer = "localhost";
		
		//设置邮件传输协议
		//生成标准的属性文件对象
		Properties props = System.getProperties();
		//设置要和哪一个服务器进行连接
		props.put("mail.smtp.host", mailServer);
		
		//建立发送邮件的连接
		//和prop的主机建立连接，同时不做其他连接null
		Session session = Session.getDefaultInstance(props, null);
		
		//在Session这个连接中创建发送信息载体
		Message msg = new MimeMessage(session);
		//创建相关的邮件属性
		try {
			//设置邮件的发件人地址
			msg.setFrom(new InternetAddress(from));
			//设置邮件的收件人地址     
			//  To:普通发送，点对点     
			//  CC：抄送，同时发多个    
			//  BCC：暗送，匿名发送
			
			String cc1 = "jerry@mymail.com";
		
			
			String bcc1 = "tom@mymail.com";

			
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//如果是使用抄送的话，就不用有TO了，直接将TO添加到Recipients里就行
			//TNND，To还是得有的，没有发布出去，但Recipients中就不要有了，要不就重复了（发了两遍）
			msg.setRecipients(Message.RecipientType.CC, new InternetAddress[]{new InternetAddress(cc1)});
			msg.setRecipients(Message.RecipientType.BCC, new InternetAddress[]{new InternetAddress(bcc1)});
			/*
				//群发
				msg.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(to),
						new InternetAddress(to_2),new InternetAddress(to_3)});
				//下面写一个循环，即每一个to的地址都set一遍，然后发送
			*/
			
			//设置邮件主题
			msg.setSubject(subject);
			//设置邮件发送时间
			msg.setSentDate(new Date());
			//设置邮件内容   纯文本的邮件不用设置类型   （信写在信封上了）
			msg.setText(content);
			
			//发送
			Transport.send(msg);
			
			System.out.print("邮件发送成功！");
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		SendMailCC.send();
	}
	
}
