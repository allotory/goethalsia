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
import com.wellmail.dao.FolderDao;
import com.wellmail.dao.MailTagDao;
import com.wellmail.dao.PriorityDao;
import com.wellmail.dao.UsersDao;
import com.wellmail.form.PageForm;
import com.wellmail.model.Attachment;
import com.wellmail.model.BCC;
import com.wellmail.model.CC;
import com.wellmail.model.Email;
import com.wellmail.model.Folder;
import com.wellmail.model.MailTag;
import com.wellmail.model.Priority;
import com.wellmail.model.Users;

public class ScriptBoxAction extends Action  {
	
	private UsersDao usersDao;
	private Users users;
	
	private PriorityDao priorityDao;
	private Priority priority;
	
	private AttachmentDao attachmentDao;
	
	private MailTagDao mailTagDao;
	private MailTag mailtag;
	
	private EmailDao emailDao;
	private Email email;
	
	private EmailBean emailbean;
	
	private Folder folder;
	private FolderDao folderDao;
	
	private CCDao ccDao;
	
	private BCCDao bccDao;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		users = (Users)request.getSession().getAttribute("user");
		users = usersDao.queryUserByName(users);
	
		folder.setFoldername("≤›∏Âœ‰");
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
		/*
		for(Iterator<EmailBean> it = emailBeanList.iterator();it.hasNext();) {
			emailbean = it.next();
			System.out.println("sender2:"+emailbean.getEmail().getSender());
			System.out.println("recipients2:"+emailbean.getEmail().getRecipients());
			System.out.println("subject2:"+emailbean.getEmail().getSubject());
		}
		*/
		
		/*int unreadCount = emailDao.queryUnreadCount(users);
		request.setAttribute("unreadCount", unreadCount);*/
		
		
		//∑÷“≥
		int pageSize = 7;
		
		PageForm pf = (PageForm)form;
		int pageNo = pf.getPageNo();
		
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

		request.getSession().setAttribute("scriptPageNo", pageNo);
		request.getSession().setAttribute("scriptTotalPages", totalPages);
		
		
		
		request.getSession().setAttribute("scriptEmailBeanList", emailBeanListTemp);
		return mapping.findForward("success");
	}

	public void setCcDao(CCDao ccDao) {
		this.ccDao = ccDao;
	}

	public void setBccDao(BCCDao bccDao) {
		this.bccDao = bccDao;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public void setFolderDao(FolderDao folderDao) {
		this.folderDao = folderDao;
	}

	public void setEmailbean(EmailBean emailbean) {
		this.emailbean = emailbean;
	}

	public void setEmailDao(EmailDao emailDao) {
		this.emailDao = emailDao;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public void setPriorityDao(PriorityDao priorityDao) {
		this.priorityDao = priorityDao;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}

	public void setMailTagDao(MailTagDao mailTagDao) {
		this.mailTagDao = mailTagDao;
	}

	public void setMailtag(MailTag mailtag) {
		this.mailtag = mailtag;
	}

}
