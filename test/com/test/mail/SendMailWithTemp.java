package com.test.mail;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

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

/*
 *发送已存在邮件
 */

public class SendMailWithTemp {

	public static void send() {
		
		//绑定邮件模板
		ResourceBundle rb = ResourceBundle.getBundle("mail");
		

		//发件人
		String from = "michael.zhang";
		//收件人
		String to = "bauble";
		//邮件主题
		String subject = "第五封测试模板邮件（解决回车错误）";
		//邮件内容
		//String content = "这里是第一封测试邮件，谢谢";
		
		//确定发送邮件服务器地址   （不包括.com）
		String mailServer = "localhost";
		
		
		Object[] arg = {to};
		MessageFormat mf = new MessageFormat("");
		mf.applyPattern(rb.getString("message"));
		//用参数填充模板
		String message = mf.format(arg);
		
		
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
			
			msg.setSentDate(new Date());
			//设置邮件的收件人地址     
			//  To:普通发送，点对点     
			//  CC：抄送，同时发多个    
			//  BCC：暗送，匿名发送
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
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
			//msg.setText(content);
			
			MimeBodyPart mbp = new MimeBodyPart();
			mbp.setContent(message,"text/html;charset=GB18030");
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp);
			
			msg.setContent(mp);
			
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
		SendMailWithTemp.send();
	}
	
}
