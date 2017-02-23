package com.test.mail;

import java.util.Date;
import java.util.Properties;

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
 * 包含HTML的邮件发送
 */

public class SendHTMLMail {

	public static void send() {

		//发件人
		String from = "severus@mymail.com";
		//收件人
		String to = "bauble@mymail.com";
		//邮件主题
		String subject = "第3封测试HTML邮件";
		//邮件内容
		String content = "<html><body>这里是<h3>第3封</h3><font color='red'>HTML</font>邮件，谢谢</body></html>";
		
		//邮件类型
		// text/plain --纯文本
		// text/html  --HTML
		String type = "text/html";
		
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
			
			//System.out.println(msg.getm());
			
			
			
			
			/*
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager
				    .getConnection("jdbc:mysql://localhost/mymail?autoReconnect=true", "root", "root");
				String sql = "insert into email values(1,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, from);
				pstmt.setString(2, to);
				pstmt.setString(3, subject);
				pstmt.setString(4, content);
				pstmt.setString(5, new Date().toString());
				pstmt.setString(6, type + ";charset=GB18030");
				pstmt.setString(7, 0.01+"");
				pstmt.setInt(8, 1);
				pstmt.setInt(9, 1);
				pstmt.setInt(10, 1);
				pstmt.setInt(11, 1);
				pstmt.setString(12, "severus");
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if(conn != null) {
					try {
						conn.close();
						conn = null;
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
			
			
			*/
			
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
		SendHTMLMail.send();
	}
	
}
