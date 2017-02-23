package com.wellmail.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wellmail.bean.EmailBean;
import com.wellmail.dao.AttachmentDao;
import com.wellmail.dao.BCCDao;
import com.wellmail.dao.CCDao;
import com.wellmail.dao.EmailDao;
import com.wellmail.dao.EmailSpaceDao;
import com.wellmail.dao.FolderDao;
import com.wellmail.dao.MailTagDao;
import com.wellmail.dao.PriorityDao;
import com.wellmail.dao.UsersDao;
import com.wellmail.form.EmailForm;
import com.wellmail.model.Attachment;
import com.wellmail.model.BCC;
import com.wellmail.model.CC;
import com.wellmail.model.Email;
import com.wellmail.model.EmailSpace;
import com.wellmail.model.Folder;
import com.wellmail.model.MailTag;
import com.wellmail.model.Priority;
import com.wellmail.model.Users;

public class AddScriptAction extends Action {
	
	private UsersDao usersDao;
	private Users users;
	
	private AttachmentDao attachmentDao;
	private Attachment attachment;
	
	private EmailDao emailDao;
	private Email email;
	
	private MailTagDao mailTagDao;
	private MailTag mailtag;
	
	private PriorityDao priorityDao;
	private Priority priority;
	
	private FolderDao folderDao;
	private Folder folder;
	
	private EmailSpaceDao emailSpaceDao;
	private EmailSpace emailspace;
	
	private CCDao ccDao;
	private CC ccs;
	
	private BCCDao bccDao;
	private BCC bccs;

	private EmailBean emailbean;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		users = (Users) request.getSession().getAttribute("user");
		users = usersDao.queryUserByName(users);
		
		// mailtag id号为9的是普通邮件，默认
		mailtag.setTagid(9);
		mailtag = mailTagDao.queryMailTagById(mailtag);

		// priority id为2的是普通邮件，默认
		priority.setPriorityid(2);
		priority = priorityDao.queryPriorityById(priority);
		
		// folder名称为草稿箱
		folder.setFoldername("草稿箱");
		folder = folderDao.queryFolderByUserAndFname(users, folder);
		
		// EmailForm
		EmailForm ef = (EmailForm) form;

		// 邮件信息
		// 收件人
		String recipients = ef.getRecipients();
		
		// 主题
		String subject = null;
		if(ef.getSubject() != null) {
			subject = new String(ef.getSubject().getBytes("ISO8859-1"),"GB18030");
		}else{
			subject = "";
		}
		
		// 内容
		String content = null;
		if(ef.getContent() != null) {
			content = new String(ef.getContent().getBytes("ISO8859-1"),"GB18030");
		}else {
			content = "";
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
		
		float filesize = 0;
		
		if(qf != null && qf.length > 0) {
			for(int i = 0; i<qf.length; i++) {
				
				email.setUser(users);
				email.setSender(users.getUsername());
				email.setRecipients(recipients);
				email.setSubject(subject);
				email.setContent(content);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String senttime = sdf.format(new Date());
				
				email.setSenttime(senttime);
				
				email.setMailtag(mailtag);
				email.setMailtype(mailtype);
				
				email.setUnread(0);
				email.setPriority(priority);
				email.setFolder(folder);
				
				File file = new File("d:/upload/temp.doc");
				FileOutputStream fos;
				try {
					fos = new FileOutputStream(file);
					OutputStreamWriter osw = new OutputStreamWriter(fos);
					BufferedWriter bw = new BufferedWriter(osw);
					bw.write(content);
					bw.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				// 读取文件大小 单位KB
				filesize = file.length();
				filesize = filesize / 1024;

				if (attach != null) {
					for (int j = 0; j < attach.length; j++) {
						File f = new File("d:/upload/"
								+ new String(attach[j].getBytes("ISO8859-1"),
										"GB18030"));

						float asize = f.length() / 1024;

						filesize += asize;
					}
				}

				// System.out.println("邮件大小：" + filesize + "KB");
				if (filesize < 1) {
					filesize = 1;
				}

				filesize = filesize / 1024;
				//System.out.println("邮件大小：" + filesize + "MB");
				
				email.setMsgsize(filesize);
				emailDao.addEmail(email);

				folder.setFolderspace(folder.getFolderspace() + filesize);
				folder.setMailcount(folder.getMailcount() + 1);
				
				// 处理附件
				if (attach != null && attach.length > 0) {
					for (int j = 0; j < attach.length; j++) {
						File f = new File("d:/upload/"
								+ new String(attach[j].getBytes("ISO8859-1"),
										"GB18030"));

						attachment.setAttachmentpath("d:/upload/"
								+ new String(attach[j].getBytes("ISO8859-1"),
										"GB18030"));
						attachment.setAttachmentname(new String(attach[j]
								.getBytes("ISO8859-1"), "GB18030"));

						float attachmentsize = f.length() / 1024;

						attachmentsize = attachmentsize / 1024;

						attachment.setAttachmentsize(attachmentsize);

						attachment.setEmail(email);

						attachmentDao.addAttachment(attachment);
					}
				}
				
				//folder大小处理
				folder.setFolderspace(folder.getFolderspace() + filesize);
				folder.setMailcount(folder.getMailcount() + 1);
				
				// 处理邮件空间
				emailspace = emailSpaceDao.querySpaceByUser(users);
				emailspace.setSpaceleft(emailspace.getSpaceleft() - filesize);
				emailSpaceDao.modifySpace(emailspace);
				
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
		}else {
			
			//没有群发
			email.setUser(users);
			email.setSender(users.getUsername());
			email.setRecipients(recipients);
			email.setSubject(subject);
			email.setContent(content);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String senttime = sdf.format(new Date());
			
			email.setSenttime(senttime);
			
			email.setMailtag(mailtag);
			email.setMailtype(mailtype);
			
			email.setUnread(0);
			email.setPriority(priority);
			email.setFolder(folder);
			
			File file = new File("d:/upload/temp.doc");
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(file);
				OutputStreamWriter osw = new OutputStreamWriter(fos);
				BufferedWriter bw = new BufferedWriter(osw);
				bw.write(content);
				bw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 读取文件大小 单位KB
			filesize = file.length();
			filesize = filesize / 1024;

			if (attach != null) {
				for (int j = 0; j < attach.length; j++) {
					File f = new File("d:/upload/"
							+ new String(attach[j].getBytes("ISO8859-1"),
									"GB18030"));

					float asize = f.length() / 1024;

					filesize += asize;
				}
			}

			// System.out.println("邮件大小：" + filesize + "KB");
			if (filesize < 1) {
				filesize = 1;
			}

			filesize = filesize / 1024;
			//System.out.println("邮件大小：" + filesize + "MB");
			
			email.setMsgsize(filesize);
			emailDao.addEmail(email);

			folder.setFolderspace(folder.getFolderspace() + filesize);
			folder.setMailcount(folder.getMailcount() + 1);
			
			// 处理附件
			if (attach != null && attach.length > 0) {
				for (int j = 0; j < attach.length; j++) {
					File f = new File("d:/upload/"
							+ new String(attach[j].getBytes("ISO8859-1"),
									"GB18030"));

					attachment.setAttachmentpath("d:/upload/"
							+ new String(attach[j].getBytes("ISO8859-1"),
									"GB18030"));
					attachment.setAttachmentname(new String(attach[j]
							.getBytes("ISO8859-1"), "GB18030"));

					float attachmentsize = f.length() / 1024;

					attachmentsize = attachmentsize / 1024;

					attachment.setAttachmentsize(attachmentsize);

					attachment.setEmail(email);

					attachmentDao.addAttachment(attachment);
				}
			}
			
			//folder大小处理
			folder.setFolderspace(folder.getFolderspace() + filesize);
			folder.setMailcount(folder.getMailcount() + 1);
			
			// 处理邮件空间
			emailspace = emailSpaceDao.querySpaceByUser(users);
			emailspace.setSpaceleft(emailspace.getSpaceleft() - filesize);
			emailSpaceDao.modifySpace(emailspace);
			
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
		
		
		users = (Users)request.getSession().getAttribute("user");
		users = usersDao.queryUserByName(users);
	
		folder.setFoldername("草稿箱");
		folder  = folderDao.queryFolderByUserAndFname(users, folder);
		
		List<Email> emailList = emailDao.queryEmailByUnameAndFolder(users, folder);
		
		List<EmailBean> emailBeanList = new ArrayList<EmailBean>();
		
		if(emailList != null) {
			for(Iterator<Email> i = emailList.iterator();i.hasNext();) {
				email = i.next();
				/*
					System.out.println("sender:"+email.getSender());
					System.out.println("recipients:"+email.getRecipients());
					System.out.println("subject:"+email.getSubject());
					System.out.println("username:"+email.getUsername());
				*/
				
				List<Attachment> attachmentList = attachmentDao.queryAttachmentByEmailId(email);
				
				List<CC> ccList = ccDao.queryCCByEmailId(email);
				
				List<BCC> bccList = bccDao.queryBCCByEmailId(email);
				
				priority.setPriorityid(email.getPriority().getPriorityid());
				priority = priorityDao.queryPriorityById(priority);
				
				mailtag.setTagid(email.getMailtag().getTagid());
				mailtag = mailTagDao.queryMailTagById(mailtag);
				
				email.setPriority(priority);
				email.setMailtag(mailtag);
				
				emailbean = new EmailBean();
				
				emailbean.setEmail(email);
				emailbean.setAttachmentList(attachmentList);
				emailbean.setCcList(ccList);
				emailbean.setBccList(bccList);
				if(attachmentList == null) {
					emailbean.setContainAttachment(false);
				}else {
					emailbean.setContainAttachment(true);
				}
				
				emailBeanList.add(emailbean);
				emailbean = null;
			}
		
		}
		
		request.getSession().setAttribute("scriptEmailBeanList", emailBeanList);
	
		return mapping.findForward("success");
	}

	public void setEmailbean(EmailBean emailbean) {
		this.emailbean = emailbean;
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

	public void setEmailSpaceDao(EmailSpaceDao emailSpaceDao) {
		this.emailSpaceDao = emailSpaceDao;
	}

	public void setEmailspace(EmailSpace emailspace) {
		this.emailspace = emailspace;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public void setEmailDao(EmailDao emailDao) {
		this.emailDao = emailDao;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public void setMailTagDao(MailTagDao mailTagDao) {
		this.mailTagDao = mailTagDao;
	}

	public void setMailtag(MailTag mailtag) {
		this.mailtag = mailtag;
	}

	public void setPriorityDao(PriorityDao priorityDao) {
		this.priorityDao = priorityDao;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void setFolderDao(FolderDao folderDao) {
		this.folderDao = folderDao;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	
}
