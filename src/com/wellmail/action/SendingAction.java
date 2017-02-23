package com.wellmail.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
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

import com.wellmail.dao.AttachmentDao;
import com.wellmail.dao.BCCDao;
import com.wellmail.dao.CCDao;
import com.wellmail.dao.EmailDao;
import com.wellmail.dao.FolderDao;
import com.wellmail.dao.MailTagDao;
import com.wellmail.dao.PriorityDao;
import com.wellmail.dao.UserScoreDao;
import com.wellmail.dao.UsersDao;
import com.wellmail.form.EmailForm;
import com.wellmail.model.Attachment;
import com.wellmail.model.BCC;
import com.wellmail.model.CC;
import com.wellmail.model.Email;
import com.wellmail.model.Folder;
import com.wellmail.model.MailTag;
import com.wellmail.model.Priority;
import com.wellmail.model.UserScore;
import com.wellmail.model.Users;

public class SendingAction extends Action {

	private UsersDao usersDao;
	private Users users;

	private UserScoreDao userScoreDao;
	private UserScore userscore;

	private FolderDao folderDao;
	private Folder folder;

	private EmailDao emailDao;
	private Email email;

	private MailTagDao mailTagDao;
	private MailTag mailtag;

	private PriorityDao priorityDao;
	private Priority priority;

	private AttachmentDao attachmentDao;
	private Attachment attachment;

	private CCDao ccDao;
	private CC ccs;

	private BCCDao bccDao;
	private BCC bccs;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 获取当前用户，同时也是发件人
		users = (Users) request.getSession().getAttribute("user");
		// System.out.println(users.getUsername());
		users = usersDao.queryUserByName(users);

		// 处理用户积分
		userscore = userScoreDao.queryScoreByUser(users);
		userscore.setScore(userscore.getScore() + 5);
		if (0 < userscore.getScore() && userscore.getScore() <= 300) {
			userscore.setLevel(1);
		} else if (301 <= userscore.getScore() && userscore.getScore() <= 1000) {
			userscore.setLevel(2);
		} else if (1001 <= userscore.getScore() && userscore.getScore() <= 5000) {
			userscore.setLevel(3);
		} else if (5001 <= userscore.getScore()
				&& userscore.getScore() <= 10000) {
			userscore.setLevel(4);
		} else if (10001 <= userscore.getScore()
				&& userscore.getScore() <= 50000) {
			userscore.setLevel(5);
		} else if (50001 <= userscore.getScore()
				&& userscore.getScore() <= 100000) {
			userscore.setLevel(6);
		} else if (100001 <= userscore.getScore()
				&& userscore.getScore() <= 500000) {
			userscore.setLevel(7);
		}
		userScoreDao.modifyScore(userscore);

		// 发送者
		String sender = users.getUsername();

		// mailtag id号为9的是普通邮件，默认
		mailtag.setTagid(9);
		mailtag = mailTagDao.queryMailTagById(mailtag);

		// priority id为2的是普通邮件，默认
		priority.setPriorityid(2);
		priority = priorityDao.queryPriorityById(priority);

		// folder id号为3是已发送，默认 ,对于当前登录用户该folder为"已发送",但是对于密送人来说当前folder为“收件箱”
		// id为1
		// folder.setFolderid(3);
		// folder = folderDao.queryFolderByUserAndId(users, folder);

		// EmailForm
		EmailForm ef = (EmailForm) form;

		// 邮件信息
		// 收件人
		String recipients = ef.getRecipients();
		// 主题
		String subject = "";
		if (ef.getSubject() != null && ef.getSubject() != "") {
			subject = new String(ef.getSubject().getBytes("ISO8859-1"),
					"Gb18030");
		} else {
			subject = "未命名邮件";
		}
		// 内容
		String content = "";
		if (ef.getContent() != null && ef.getContent() != "") {
			content = new String(ef.getContent().getBytes("ISO8859-1"),
					"Gb18030");
		} else {
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
		String qf2[] = null;
		if (qftemp != null && qftemp != "") {
			qf = new String[qftemp.toCharArray().length];
			qf = qftemp.split(";");
			// for(int i = 0; i<qf.length; i++) {
			// System.out.println("qf"+i+":"+qf[i]);
			// }
			qf2 = new String[qf.length + 1];
			for (int i = 0; i < qf.length; i++) {
				qf2[i] = qf[i];
			}
			qf2[qf.length] = recipients;

		}
		// for(int i = 0 ; i< qf2.length; i++) {
		// System.out.println("qf2"+i+":"+qf2[i]);
		// }

		// 附件地址数组
		String attach[] = request.getParameterValues("attach");

		/*
		 * for(int i = 0; i<attach.length; i++) {
		 * 
		 * System.out.println("attach :"+new
		 * String(attach[i].getBytes("ISO8859-1"),"GB18030")); }
		 */
		if (qf2 != null) {

			for (int q = 0; q < qf2.length; q++) {

				/**
				 * --------------------------------------该封邮件没有密送人，不用保存------------------------------------------
				 */

				System.out.println("该封邮件没有密送人，不用保存");

				/*
				 * File file2 = new File("d:/upload/temp.doc"); FileOutputStream
				 * fos2; try { fos2 = new FileOutputStream(file2);
				 * OutputStreamWriter osw2 = new OutputStreamWriter(fos2);
				 * BufferedWriter bw2 = new BufferedWriter(osw2);
				 * bw2.write(content); bw2.close(); } catch
				 * (FileNotFoundException e) { e.printStackTrace(); } catch
				 * (IOException e) { e.printStackTrace(); } // 读取文件大小 单位KB
				 * filesize2 = file2.length(); filesize2 = filesize2 / 1024;
				 * 
				 * if (attach != null) { for (int j = 0; j < attach.length; j++) {
				 * File f = new File("d:/upload/" + new
				 * String(attach[j].getBytes("ISO8859-1"), "GB18030"));
				 * 
				 * float asize2 = f.length() / 1024;
				 * 
				 * filesize2 += asize2; } }
				 * 
				 * System.out.println("邮件大小：" + filesize2 + "KB"); if (filesize2 <
				 * 1) { filesize2 = 1; }
				 * 
				 * filesize2 = filesize2 / 1024; System.out.println("邮件大小：" +
				 * filesize2 + "MB");
				 * 
				 */

				// 保存发件人信息
				users.setUsername(sender);
				users = usersDao.queryUserByName(users);

				email.setUser(users);
				email.setSender(sender);
				email.setRecipients(qf2[q]);
				email.setSubject(subject);
				email.setContent(content);

				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String senttime = sdf.format(new Date());
				email.setSenttime(senttime);

				email.setMailtype(mailtype);
				email.setMailtag(mailtag);
				email.setMsgsize(0);
				email.setUnread(0);
				email.setMailtag(mailtag);
				email.setPriority(priority);

				folder.setFoldername("已发送");
				folder = folderDao.queryFolderByUserAndFname(users, folder);
				folder.setMailcount(folder.getMailcount() + 1);
				folderDao.modifyFolder(folder);
				email.setFolder(folder);

				emailDao.addEmail(email);

				// 处理附件
				if (attach != null && attach.length > 0) {
					for (int j = 0; j < attach.length; j++) {
						File f = new File(
								"../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
										+ new String(attach[j]
												.getBytes("ISO8859-1"),
												"GB18030"));
						System.out.println("attach3"
								+ new String(attach[j].getBytes("ISO8859-1"),
										"GB18030"));
						attachment
								.setAttachmentpath("../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
										+ new String(attach[j]
												.getBytes("ISO8859-1"),
												"GB18030"));
						attachment.setAttachmentname(new String(attach[j]
								.getBytes("ISO8859-1"), "GB18030"));

						double attachmentsize = f.length() / 1024;

						attachmentsize = attachmentsize / 1024;

						DecimalFormat df = new DecimalFormat(".###");
						String st = df.format(attachmentsize);
						attachmentsize = Double.parseDouble(st);

						attachment.setAttachmentsize(attachmentsize);

						attachment.setEmail(email);

						attachmentDao.addAttachment(attachment);
					}
				}

				/*// 处理抄送人cc
				if (cc != null) {
					for (int k = 0; k < cc.length; k++) {
						ccs.setCcname(cc[k]);
						ccs.setEmail(email);
						ccDao.addCC(ccs);
					}
				}

				// 处理密送人bcc
				if (bcc != null) {
					for (int k = 0; k < bcc.length; k++) {
						bccs.setBccname(bcc[k]);
						bccs.setEmail(email);
						bccDao.addBCC(bccs);
					}
				}*/
			}

		} else {
			// 不群发 分为有密送人和没有密送人两部分

			if (null != ef.getBcc() && "" != ef.getBcc()) {

				// 该封邮件有密送人（单独保存）

				for (int i = 0; i < bcc.length; i++) {
					// 密送人信息
					users.setUsername(bcc[i]);
					users = usersDao.queryUserByName(users);

					email.setUser(users);
					email.setSender(sender);
					email.setRecipients(recipients);
					email.setSubject(subject);
					email.setContent(content);

					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					String senttime = sdf.format(new Date());
					email.setSenttime(senttime);

					/*
					 * File file = new File("d:/upload/temp.doc");
					 * FileOutputStream fos; try { fos = new
					 * FileOutputStream(file); OutputStreamWriter osw = new
					 * OutputStreamWriter(fos); BufferedWriter bw = new
					 * BufferedWriter(osw); bw.write(content); bw.close(); }
					 * catch (FileNotFoundException e) { e.printStackTrace(); }
					 * catch (IOException e) { e.printStackTrace(); } // 读取文件大小
					 * 单位KB filesize = file.length(); filesize = filesize /
					 * 1024;
					 * 
					 * if (attach != null) { for (int j = 0; j < attach.length;
					 * j++) { File f = new File("d:/upload/" + new
					 * String(attach[j].getBytes("ISO8859-1"), "GB18030"));
					 * 
					 * float asize = f.length() / 1024;
					 * 
					 * filesize += asize; } }
					 * 
					 * System.out.println("邮件大小：" + filesize + "KB"); if
					 * (filesize < 1) { filesize = 1; }
					 * 
					 * filesize = filesize / 1024; System.out.println("邮件大小：" +
					 * filesize + "MB");
					 */

					email.setMsgsize(0);
					email.setMailtype(mailtype);
					email.setUnread(1);
					email.setMailtag(mailtag);
					email.setPriority(priority);

					// 处理当前email的文件夹
					folder.setFoldername("收件箱");
					folder = folderDao.queryFolderByUserAndFname(users, folder);
					folder.setMailcount(folder.getMailcount() + 1);
					folderDao.modifyFolder(folder);
					email.setFolder(folder);

					emailDao.addEmail(email);

					// 处理邮件空间
					// emailspace = emailSpaceDao.querySpaceByUser(users);
					// emailSpaceDao.modifySpace(emailspace);

					// 处理抄送人cc
					if (cc != null) {
						for (int k = 0; k < cc.length; k++) {
							ccs.setCcname(cc[k]);
							ccs.setEmail(email);
							ccDao.addCC(ccs);
						}
					}

					// 处理附件
					if (attach != null && attach.length > 0) {
						for (int j = 0; j < attach.length; j++) {
							File f = new File(
									"../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
											+ new String(attach[j]
													.getBytes("ISO8859-1"),
													"GB18030"));
							System.out.println("attach 2"
									+ new String(attach[j]
											.getBytes("ISO8859-1"), "GB18030"));
							attachment
									.setAttachmentpath("../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
											+ new String(attach[j]
													.getBytes("ISO8859-1"),
													"GB18030"));
							attachment.setAttachmentname(new String(attach[j]
									.getBytes("ISO8859-1"), "GB18030"));

							double attachmentsize = f.length() / 1024;

							attachmentsize = attachmentsize / 1024;

							DecimalFormat df = new DecimalFormat(".###");
							String st = df.format(attachmentsize);
							attachmentsize = Double.parseDouble(st);

							attachment.setAttachmentsize(attachmentsize);

							attachment.setEmail(email);

							attachmentDao.addAttachment(attachment);
						}
					}
				}

				/**
				 * ---------------------------------保存发件人信息(有密送人)--------------------------------
				 */
				users.setUsername(sender);
				users = usersDao.queryUserByName(users);

				email.setUser(users);
				email.setSender(sender);
				email.setRecipients(recipients);
				email.setSubject(subject);
				email.setContent(content);

				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String senttime = sdf.format(new Date());
				email.setSenttime(senttime);

				email.setMailtype(mailtype);
				email.setMailtag(mailtag);
				email.setMsgsize(0);
				email.setUnread(0);
				email.setMailtag(mailtag);
				email.setPriority(priority);

				folder.setFoldername("已发送");
				folder = folderDao.queryFolderByUserAndFname(users, folder);
				folder.setMailcount(folder.getMailcount() + 1);
				folderDao.modifyFolder(folder);
				email.setFolder(folder);

				emailDao.addEmail(email);

				// 处理邮件空间
				/*
				 * emailspace = emailSpaceDao.querySpaceByUser(users);
				 * emailspace.setSpaceleft(emailspace.getSpaceleft() -
				 * filesize); emailSpaceDao.modifySpace(emailspace);
				 */

				// 处理附件
				if (attach != null && attach.length > 0) {
					for (int j = 0; j < attach.length; j++) {
						File f = new File(
								"../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
										+ new String(attach[j]
												.getBytes("ISO8859-1"),
												"GB18030"));
						System.out.println("attach3"
								+ new String(attach[j].getBytes("ISO8859-1"),
										"GB18030"));
						attachment
								.setAttachmentpath("../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
										+ new String(attach[j]
												.getBytes("ISO8859-1"),
												"GB18030"));
						attachment.setAttachmentname(new String(attach[j]
								.getBytes("ISO8859-1"), "GB18030"));

						double attachmentsize = f.length() / 1024;

						attachmentsize = attachmentsize / 1024;

						DecimalFormat df = new DecimalFormat(".###");
						String st = df.format(attachmentsize);
						attachmentsize = Double.parseDouble(st);

						attachment.setAttachmentsize(attachmentsize);

						attachment.setEmail(email);

						attachmentDao.addAttachment(attachment);
					}
				}

				// 处理抄送人cc
				if (cc != null) {
					for (int k = 0; k < cc.length; k++) {
						ccs.setCcname(cc[k]);
						ccs.setEmail(email);
						ccDao.addCC(ccs);
					}
				}

				// 处理密送人bcc
				if (bcc != null) {
					for (int k = 0; k < bcc.length; k++) {
						bccs.setBccname(bcc[k]);
						bccs.setEmail(email);
						bccDao.addBCC(bccs);
					}
				}

			} else {

				/**
				 * --------------------------------------该封邮件没有密送人，不用保存------------------------------------------
				 */

				System.out.println("该封邮件没有密送人，不用保存");

				/*
				 * File file2 = new File("d:/upload/temp.doc"); FileOutputStream
				 * fos2; try { fos2 = new FileOutputStream(file2);
				 * OutputStreamWriter osw2 = new OutputStreamWriter(fos2);
				 * BufferedWriter bw2 = new BufferedWriter(osw2);
				 * bw2.write(content); bw2.close(); } catch
				 * (FileNotFoundException e) { e.printStackTrace(); } catch
				 * (IOException e) { e.printStackTrace(); } // 读取文件大小 单位KB
				 * filesize2 = file2.length(); filesize2 = filesize2 / 1024;
				 * 
				 * if (attach != null) { for (int j = 0; j < attach.length; j++) {
				 * File f = new File("d:/upload/" + new
				 * String(attach[j].getBytes("ISO8859-1"), "GB18030"));
				 * 
				 * float asize2 = f.length() / 1024;
				 * 
				 * filesize2 += asize2; } }
				 * 
				 * System.out.println("邮件大小：" + filesize2 + "KB"); if (filesize2 <
				 * 1) { filesize2 = 1; }
				 * 
				 * filesize2 = filesize2 / 1024; System.out.println("邮件大小：" +
				 * filesize2 + "MB");
				 * 
				 */

				// 保存发件人信息
				users.setUsername(sender);
				users = usersDao.queryUserByName(users);

				email.setUser(users);
				email.setSender(sender);
				email.setRecipients(recipients);
				email.setSubject(subject);
				email.setContent(content);

				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String senttime = sdf.format(new Date());
				email.setSenttime(senttime);

				email.setMailtype(mailtype);
				email.setMailtag(mailtag);
				email.setMsgsize(0);
				email.setUnread(0);
				email.setMailtag(mailtag);
				email.setPriority(priority);

				folder.setFoldername("已发送");
				folder = folderDao.queryFolderByUserAndFname(users, folder);
				folder.setMailcount(folder.getMailcount() + 1);
				folderDao.modifyFolder(folder);
				email.setFolder(folder);

				emailDao.addEmail(email);

				// 处理附件
				if (attach != null && attach.length > 0) {
					for (int j = 0; j < attach.length; j++) {
						File f = new File(
								"../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
										+ new String(attach[j]
												.getBytes("ISO8859-1"),
												"GB18030"));
						System.out.println("attach3"
								+ new String(attach[j].getBytes("ISO8859-1"),
										"GB18030"));
						attachment
								.setAttachmentpath("../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
										+ new String(attach[j]
												.getBytes("ISO8859-1"),
												"GB18030"));
						attachment.setAttachmentname(new String(attach[j]
								.getBytes("ISO8859-1"), "GB18030"));

						double attachmentsize = f.length() / 1024;

						attachmentsize = attachmentsize / 1024;

						DecimalFormat df = new DecimalFormat(".###");
						String st = df.format(attachmentsize);
						attachmentsize = Double.parseDouble(st);

						attachment.setAttachmentsize(attachmentsize);

						attachment.setEmail(email);

						attachmentDao.addAttachment(attachment);
					}
				}

				// 处理抄送人cc
				if (cc != null) {
					for (int k = 0; k < cc.length; k++) {
						ccs.setCcname(cc[k]);
						ccs.setEmail(email);
						ccDao.addCC(ccs);
					}
				}

				// 处理密送人bcc
				if (bcc != null) {
					for (int k = 0; k < bcc.length; k++) {
						bccs.setBccname(bcc[k]);
						bccs.setEmail(email);
						bccDao.addBCC(bccs);
					}
				}
			}

		}

		/**
		 * 
		 * ------------------------------------------
		 * 正式邮件发送-----------------------------------------
		 * 
		 */

		if (qf2 != null) {

			// 群发人数组不为空
			InternetAddress[] address=new InternetAddress[qf2.length];//生成收件人地址数组 

			for (int n = 0; n < qf2.length; n++) {
				address[n] = new InternetAddress(qf2[n]);
				
			}

				// 确定发送邮件服务器地址 （不包括.com）
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
					msg.setRecipients(Message.RecipientType.TO,
							address);

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
						mbpContent.setContent(content, mailtype
								+ ";charset=GB18030");

						for (int i = 0; i < attach.length; i++) {

							// 对邮件中的附件进行处理
							MimeBodyPart mbpAttach = new MimeBodyPart();
							// 建立文件读取数据源
							DataSource fds = new FileDataSource(
									"../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
											+ new String(attach[i]
													.getBytes("ISO8859-1"),
													"GB18030"));
							// 获取（读出）整个附件的信息，此时mbp2里存储的是二进制的流所读出来的字节
							DataHandler dh = new DataHandler(fds);
							mbpAttach.setDataHandler(dh);
							// 开始读
							mbpAttach.setFileName(MimeUtility.encodeText(fds
									.getName(), "GB18030", "B"));

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
					msg = null;

				} catch (AddressException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

		} else {
			// 不群发！

			// 确定发送邮件服务器地址 （不包括.com）
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
				msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
						recipients));

				// 存在抄送人
				if (cc != null && cc.length > 0) {

					InternetAddress[] ia = new InternetAddress[cc.length];
					for (int c = 0; c < cc.length; c++) {
						ia[c] = new InternetAddress(cc[c]);
					}

					// 如果是使用抄送的话，就不用有TO了，直接将TO添加到Recipients里就行
					// TNND，To还是得有的，没有发布出去，但Recipients中就不要有了，要不就重复了（发了两遍）
					msg.setRecipients(Message.RecipientType.CC, ia);
				}

				// 存在密送人
				if (bcc != null && bcc.length > 0) {

					InternetAddress[] ia = new InternetAddress[bcc.length];
					for (int b = 0; b < bcc.length; b++) {
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
					mbpContent.setContent(content, mailtype
							+ ";charset=GB18030");

					for (int i = 0; i < attach.length; i++) {

						// 对邮件中的附件进行处理
						MimeBodyPart mbpAttach = new MimeBodyPart();
						// 建立文件读取数据源
						DataSource fds = new FileDataSource(
								"../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
										+ new String(attach[i]
												.getBytes("ISO8859-1"),
												"GB18030"));
						// 获取（读出）整个附件的信息，此时mbp2里存储的是二进制的流所读出来的字节
						DataHandler dh = new DataHandler(fds);
						mbpAttach.setDataHandler(dh);
						// 开始读
						mbpAttach.setFileName(MimeUtility.encodeText(fds
								.getName(), "GB18030", "B"));

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

		request.setAttribute("flag", "sentsuccess");
		return mapping.findForward("success");
	}

	public void setCcDao(CCDao ccDao) {
		this.ccDao = ccDao;
	}

	public void setCcs(CC ccs) {
		this.ccs = ccs;
	}

	public void setBccDao(BCCDao bccDao) {
		this.bccDao = bccDao;
	}

	public void setBccs(BCC bccs) {
		this.bccs = bccs;
	}

	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public void setPriorityDao(PriorityDao priorityDao) {
		this.priorityDao = priorityDao;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void setMailTagDao(MailTagDao mailTagDao) {
		this.mailTagDao = mailTagDao;
	}

	public void setMailtag(MailTag mailtag) {
		this.mailtag = mailtag;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public void setUserScoreDao(UserScoreDao userScoreDao) {
		this.userScoreDao = userScoreDao;
	}

	public void setUserscore(UserScore userscore) {
		this.userscore = userscore;
	}

	public void setFolderDao(FolderDao folderDao) {
		this.folderDao = folderDao;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public void setEmailDao(EmailDao emailDao) {
		this.emailDao = emailDao;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

}
