package com.wellmail.action;

import java.io.UnsupportedEncodingException;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wellmail.bean.MyAutherticator;
import com.wellmail.form.EmailForm;
import com.wellmail.model.OtherMailBoxType;

public class OtherMailSendingAction extends Action {


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		EmailForm ef = (EmailForm) form;
		// 发送者
		String sender = (String)request.getSession().getAttribute("othermailwriteuname");
		if(sender == null) {
			System.out.println("对不起，uname为空");
		}else{
			System.out.println(sender);
		}
		// 邮件信息
		// 收件人
		String recipients = ef.getRecipients();
		// 主题
		String subject = "";
		if(ef.getSubject()!= null && ef.getSubject() != "") {
			subject = new String(ef.getSubject().getBytes("ISO8859-1"),"Gb18030");
		}else {
			subject = "未命名邮件";
		}
		// 内容
		String content = "";
		if(ef.getContent() != null && ef.getContent() != "") {
			content = new String(ef.getContent().getBytes("ISO8859-1"),"Gb18030");
		}else {
			content = "系统消息：该邮件为无内容邮件！";
		}
		// 邮件类型
		String mailtype = "text/html";

		// bcc密送人列表
		String bcctemp = ef.getBcc();
		String bcc[] = null;
		if (null != bcctemp && "" != bcctemp) {
			bcc = bcctemp.split(";");
		}
		// cc抄送人列表
		String cctemp = ef.getCc();
		String cc[] = null;
		if (null != cctemp && "" != cctemp) {
			cc = cctemp.split(";");
		}
		// 群发单显列表
		String qftemp = ef.getQf();

		String qf[] = null;
		if (qftemp != null && qftemp != "") {
			qf = new String[qftemp.toCharArray().length];
			qf = qftemp.split(";");
		}

		// 附件地址数组
		String attach[] = request.getParameterValues("attach");
		
		OtherMailBoxType omt = (OtherMailBoxType) request.getSession().getAttribute("othermailwritetype");
		
		String smtp = omt.getTsmtp();
		smtp = smtp.substring(5);
		
		sender = sender +"@"+smtp;
		System.out.println(sender);
		
		/**
		 * 
		 * ------------------------------------------ 正式邮件发送-----------------------------------------
		 * 
		 */

		// 确定发送邮件服务器地址 （不包括.com）

		// 设置邮件传输协议
		// 生成标准的属性文件对象
		Properties props = System.getProperties();
		// 设置要和哪一个服务器进行连接
		props.put("mail.smtp.host", omt.getTsmtp());
		// 设置smtp端口
		props.put("mail.smtp.port", "25");
		// 发邮件协议
		props.put("mail.transport.protocol", "smtp");
		// 收邮件协议
		props.put("mail.store.protocol", "pop3");

		// 是否smtp认证
		props.put("mail.smtp.auth", "true");

		// 建立发送邮件的连接
		MyAutherticator myauth = new MyAutherticator(sender, "severus125874693");
		// 和prop的主机建立连接，同时不做其他连接null
		Session session = Session.getDefaultInstance(props, myauth);

		// 在Session这个连接中创建发送信息载体
		Message msg = new MimeMessage(session);
		
		
		if(qf != null ) {
			
			//群发人数组不为空
			
			for(int n = 0; n<qf.length; n++) {
		
				// 创建相关的邮件属性
				try {
					// 设置邮件的发件人地址
					msg.setFrom(new InternetAddress(sender));
					
					// 设置邮件的收件人地址
					// To:普通发送，点对点
					// CC：抄送，同时发多个
					// BCC：暗送，匿名发送
					msg.setRecipient(Message.RecipientType.TO, new InternetAddress(qf[n]));		
		
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
		
						// 附件为空 ，或者附件的长度小于1
		
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
							DataSource fds = new FileDataSource("../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"+new String(attach[i].getBytes("ISO8859-1"),"GB18030"));
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
		
					// 发送
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
		
		}else {
			
			//不群发！
			
			// 创建相关的邮件属性
			try {
				// 设置邮件的发件人地址
				msg.setFrom(new InternetAddress(sender));
				
				// 设置邮件的收件人地址
				// To:普通发送，点对点
				// CC：抄送，同时发多个
				// BCC：暗送，匿名发送
				msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
				
				//存在抄送人
				if(cc != null && cc.length > 0) {
					
					InternetAddress[] ia = new InternetAddress[cc.length]; 
					for(int c = 0; c < cc.length ; c++) {
						ia[c] = new InternetAddress(cc[c]);
					}
				
					//如果是使用抄送的话，就不用有TO了，直接将TO添加到Recipients里就行
					//TNND，To还是得有的，没有发布出去，但Recipients中就不要有了，要不就重复了（发了两遍）
					msg.setRecipients(Message.RecipientType.CC, ia);
				}
	
				//存在密送人
				if(bcc != null && bcc.length > 0) {
					
					InternetAddress[] ia = new InternetAddress[bcc.length]; 
					for(int b = 0; b < bcc.length ; b++) {
						ia[b] = new InternetAddress(bcc[b]);
					}
				
					msg.setRecipients(Message.RecipientType.BCC, ia);
				}
				
				
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
						DataSource fds = new FileDataSource("../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"+new String(attach[i].getBytes("ISO8859-1"),"GB18030"));
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
	
				// 发送
				//Transport.send(msg);

				System.out.print("邮件发送成功！");
	
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		}

		request.setAttribute("flag", "sentsuccess");
		return mapping.findForward("success");
	}


}
