package com.wellmail.action;

import java.util.ArrayList;
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

public class AdMailDeletingAction extends Action {
	
	private UsersDao usersDao;
	private Users users;
	
	private AttachmentDao attachmentDao;
	private Attachment attachment;
	
	private EmailDao emailDao;
	private Email email;
	
	private FolderDao folderDao;
	private Folder folder;
	
	private CCDao ccDao;
	private CC ccs;
	
	private BCCDao bccDao;
	private BCC bccs;
	
	private EmailSpaceDao emailSpaceDao;
	private EmailSpace emailspace;
	
	private MailTagDao mailTagDao;
	private MailTag mailtag;
	
	private PriorityDao priorityDao;
	private Priority priority;
	
	private EmailBean emailbean;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		users = (Users) request.getSession().getAttribute("user");
		users = usersDao.queryUserByName(users);
		
		EmailForm ef = (EmailForm)form;
			int emailid = ef.getEmailid();
			
			email = emailDao.queryEmailById(emailid);
			
			// 由emailid查询出cc列表 并逐一删除
			List<CC> ccList = new ArrayList<CC>();
			ccList = ccDao.queryCCByEmailId(email);
			if(ccList != null && ccList.size() > 0) {
				for(Iterator<CC> it = ccList.iterator();it.hasNext();) {
					ccs = it.next();
					ccDao.delCC(ccs);
				}
			}
			
			// 由emailid查询出bcc列表 并逐一删除
			List<BCC> bccList = new ArrayList<BCC>();
			bccList = bccDao.queryBCCByEmailId(email);
			if(bccList != null && bccList.size() > 0) {
				for(Iterator<BCC> it = bccList.iterator();it.hasNext();) {
					bccs = it.next();
					bccDao.delBCC(bccs);
				}
			}
			
			// 由folderid查询出folder，修改folder大小及文件数量
			folder = folderDao.queryFolderByFolderId(email.getFolder());
			folder.setFolderspace(folder.getFolderspace() - email.getMsgsize());
			folder.setMailcount(folder.getMailcount() - 1);
			folderDao.modifyFolder(folder);
			
			
			// 由emailid查询出附件列表 并逐一删除
			List<Attachment> attachmentList = new ArrayList<Attachment>();
			attachmentList = attachmentDao.queryAttachmentByEmailId(email);
			if(attachmentList != null && attachmentList.size() > 0) {
				for(Iterator<Attachment> it = attachmentList.iterator();it.hasNext();) {
					attachment = it.next();
					attachmentDao.delAttachment(attachment);
				}
			}
			
			// 由用户查出邮件空间并修改大小
			emailspace = emailSpaceDao.querySpaceByUser(users);
			emailspace.setSpaceleft(emailspace.getSpaceleft() + email.getMsgsize());
			emailSpaceDao.modifySpace(emailspace);
			
			
			//删除email
			emailDao.delEmail(email);
			

		users = (Users)request.getSession().getAttribute("user");
		users = usersDao.queryUserByName(users);
		
		folder.setFoldername("广告邮件");
		folder = folderDao.queryFolderByUserAndFname(users, folder);
		
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
				
				List<Attachment> attachmentLists = attachmentDao.queryAttachmentByEmailId(email);
				
				List<CC> ccLists = ccDao.queryCCByEmailId(email);
				List<BCC> bccLists = bccDao.queryBCCByEmailId(email);
				
				priority.setPriorityid(email.getPriority().getPriorityid());
				priority = priorityDao.queryPriorityById(priority);
				
				mailtag.setTagid(email.getMailtag().getTagid());
				mailtag = mailTagDao.queryMailTagById(mailtag);
				
				email.setPriority(priority);
				email.setMailtag(mailtag);
				
				emailbean = new EmailBean();
				
				emailbean.setEmail(email);
				emailbean.setAttachmentList(attachmentLists);
				emailbean.setCcList(ccLists);
				emailbean.setBccList(bccLists);
				if(attachmentList == null) {
					emailbean.setContainAttachment(false);
				}else {
					emailbean.setContainAttachment(true);
				}
				
				//System.out.println("subject:"+email.getSubject());
				
				emailBeanList.add(emailbean);
				emailbean = null;
			}
		}
		/*
		for(Iterator<EmailBean> it = emailBeanList.iterator();it.hasNext();) {
			emailbean = it.next();
			System.out.println("sender2:"+emailbean.getEmail().getSender());
			System.out.println("recipients2:"+emailbean.getEmail().getRecipients());
			System.out.println("subject2:"+emailbean.getEmail().getSubject());
		}
		*/
		
		
		//分页
		int pageSize = 7;
		
		int pageNo = ef.getPageNo();
		
		if(pageNo <= 0) {
			pageNo = 1;
		}
		
		int totalRecords = emailBeanList.size();
		//System.out.println(totalRecords+"____totalRecords");
		
		int totalPages = totalRecords%pageSize == 0? totalRecords/pageSize : totalRecords/pageSize +1;
		
		if(pageNo > totalPages) {
			pageNo = totalPages;
		}
		
		int startPos = (pageNo - 1) * pageSize;
		//System.out.println(startPos+"____startPos");
		
		List<EmailBean> emailBeanListTemp = new ArrayList<EmailBean>();
		
		//System.out.println(pageNo+"*****");
		for(int i = 0; i<pageNo-1; i++) {
			pageSize += 7;
		}
		//System.out.println(pageSize+"****");
		
		if(startPos >= totalRecords - totalRecords%pageSize) {
			pageSize = totalRecords%pageSize;
		}
		if(emailBeanList.size() > 0) {
			for(int i = startPos; i < pageSize; i++) {
				emailBeanListTemp.add(emailBeanList.get(i));
				//System.out.println(articles.get(i)+"--------");
			}
		}

		request.getSession().setAttribute("adPageNo", pageNo);
		request.getSession().setAttribute("adTotalPages", totalPages);
		
		request.getSession().setAttribute("adEmailBeanList", emailBeanListTemp);
		
		
		return mapping.findForward("success");
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

	public void setEmailbean(EmailBean emailbean) {
		this.emailbean = emailbean;
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

	public void setFolderDao(FolderDao folderDao) {
		this.folderDao = folderDao;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
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
}
