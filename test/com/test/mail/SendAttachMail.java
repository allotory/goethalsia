package com.test.mail;

import java.io.UnsupportedEncodingException;
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

/*
 * 包含附件的邮件发送
 */

public class SendAttachMail {

	public static void send() {

		//发件人
		String from = "severus@mymail.com";
		//收件人
		String to = "bauble@mymail.com";
		//邮件主题
		String subject = "第1封测试带附件的邮件";
		//邮件内容
		String content = "<html><body>这里是第<font color='red'>1</font>封测试带附件的邮件，谢谢</body></html>";
		
		//邮件类型
		// text/plain --纯文本
		// text/html  --HTML
		String type = "text/html";
		
		//附件文件名
		String fileName = "D:/aaa.txt";
		String fileName2 = "D:/java开发笔记.doc";
		
		
		//确定发送邮件服务器地址   （不包括.com）
		String mailServer = "localhost";
		
		//设置邮件传输协议
		//生成标准的属性文件对象
		Properties props = System.getProperties();
		//设置要和哪一个服务器进行连接
		props.put("mail.smtp.host", mailServer);
	    /*  // 是否smtp认证   
	        props.put("mail.smtp.auth", "true"); 
	        // 设置smtp端口   
	        props.put("mail.smtp.port", "25"); 
	        // 发邮件协议   
	        props.put("mail.transport.protocol", "smtp"); 
	        // 收邮件协议   
	        props.put("mail.store.protocol", "pop3"); 
        */
		
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

			//处理附件
			if((fileName == null || fileName == "")&&(fileName2 == null || fileName2 == "") ) {
			
				//判断发送的Mime类型
				//Multipart用于传输二进制数据   mp的组成不是一个整体组成，而是以块或部分的组成
				//使用setText发送的邮件相当一个“明信片”——只有信封，没有信。而Multipart相当于一个“信”，里面有信的body
				//（一个“信”可以有多个body），mbp.setContent是将content设置为type类型
				Multipart mp = new MimeMultipart();
				MimeBodyPart mbp = new MimeBodyPart();
				//设置邮件发送制定的类型
				//setContent相当与将content设置为type类型
				mbp.setContent(content,type + ";charset=GB18030");
				//将“信体”添加进“信”里
				mp.addBodyPart(mbp);
				//将“信”加到“信封”（msg）里
				msg.setContent(mp);
				
			}else {
				//保存草稿  用自己的流再保存一次
				//File file = new File(fileName);

				//对邮件的普通内容进行处理
				Multipart mp = new MimeMultipart();
				
				
				MimeBodyPart mbp1 = new MimeBodyPart();
				mbp1.setContent(content,type + ";charset=GB18030");
				
				
				//对邮件中的附件进行处理
				MimeBodyPart mbp2 = new MimeBodyPart();
				//建立文件读取数据源
				DataSource fds = new FileDataSource(fileName);
				//获取（读出）整个附件的信息，此时mbp2里存储的是二进制的流所读出来的字节
				DataHandler dh = new DataHandler(fds);
				mbp2.setDataHandler(dh);
				//开始读
				mbp2.setFileName(MimeUtility.encodeText(fds.getName(),"GB18030","B"));
				
				
				MimeBodyPart mbp3 = new MimeBodyPart();
				DataSource fds2 = new FileDataSource(fileName2);
				DataHandler dh2 = new DataHandler(fds2);
				mbp3.setDataHandler(dh2);
				mbp3.setFileName(MimeUtility.encodeText(fds2.getName(),"GB18030","B"));
				
				//添加主体
				mp.addBodyPart(mbp1);
				mp.addBodyPart(mbp2);
				mp.addBodyPart(mbp3);
				
				//添加到“信封”中
				msg.setContent(mp);
			}
			
			//发送
			Transport.send(msg);
			
			System.out.print("邮件发送成功！");
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		SendAttachMail.send();
	}
	
}
