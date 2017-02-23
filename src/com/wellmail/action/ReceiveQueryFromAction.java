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
import com.wellmail.dao.CCDao;
import com.wellmail.dao.EmailDao;
import com.wellmail.dao.FolderDao;
import com.wellmail.dao.MailTagDao;
import com.wellmail.dao.PriorityDao;
import com.wellmail.dao.UsersDao;
import com.wellmail.form.EmailForm;
import com.wellmail.model.Attachment;
import com.wellmail.model.CC;
import com.wellmail.model.Email;
import com.wellmail.model.Folder;
import com.wellmail.model.MailTag;
import com.wellmail.model.Priority;
import com.wellmail.model.Users;

public class ReceiveQueryFromAction extends Action {
	
	private UsersDao usersDao;
	private Users users;
	
	private EmailDao emailDao;
	private Email email;
	
	private MailTagDao mailTagDao;
	private MailTag mailtag;
	
	private PriorityDao priorityDao;
	private Priority priority;

	private AttachmentDao attachmentDao;
	
	private CCDao ccDao;
	
	private EmailBean emailbean;
	
	private FolderDao folderDao;
	private Folder folder;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		users = (Users) request.getSession().getAttribute("user");
		users = usersDao.queryUserByName(users);
		
		folder.setFoldername("收件箱");
		folder = folderDao.queryFolderByUserAndFname(users, folder);
		
		EmailForm ef = (EmailForm) form;
		String queryfrom = ef.getQueryfrom();
		
		List<Email> emailList = new ArrayList<Email>();
		
		if(!queryfrom.equals("all") && !queryfrom.equals("none")) {
			
			/**
			 * 按是否已读查看
			 */
			if(queryfrom.equals("readtrue")) {
				emailList = emailDao.queryEmailByRead(users,folder);
			}
			
			if(queryfrom.equals("readfalse")) {
				emailList = emailDao.queryEmailByUnread(users,folder);
			}
			
			/**
			 * 按优先级查看
			 */
			if(queryfrom.equals("urgent")) {
				priority.setPriorityname("紧急邮件");
				priority = priorityDao.queryPriorityByName(priority);
				
				emailList = emailDao.queryEmailByPriority(priority,folder,users);
			}
			
			if(queryfrom.equals("commonpriority")) {
				priority.setPriorityname("普通邮件");
				priority = priorityDao.queryPriorityByName(priority);
				
				emailList = emailDao.queryEmailByPriority(priority,folder,users);
			}
			
			if(queryfrom.equals("slow")) {
				priority.setPriorityname("缓慢邮件");
				priority = priorityDao.queryPriorityByName(priority);
				
				emailList = emailDao.queryEmailByPriority(priority,folder,users);
			}
			
			/**
			 * 按邮件标签查看
			 */
			if(queryfrom.equals("important")) {
				
				mailtag.setTagname("重要邮件");
				mailtag = mailTagDao.queryMailTagByName(mailtag);
				
				emailList = emailDao.queryEmailByTagId(mailtag,folder,users);
			}
			
			if(queryfrom.equals("company")) {
				
				mailtag.setTagname("公司邮件");
				mailtag = mailTagDao.queryMailTagByName(mailtag);
				
				emailList = emailDao.queryEmailByTagId(mailtag,folder,users);
			}

			if(queryfrom.equals("business")) {
				
				mailtag.setTagname("业务邮件");
				mailtag = mailTagDao.queryMailTagByName(mailtag);
				
				emailList = emailDao.queryEmailByTagId(mailtag,folder,users);
			}

			if(queryfrom.equals("information")) {
				
				mailtag.setTagname("资讯邮件");
				mailtag = mailTagDao.queryMailTagByName(mailtag);
				
				emailList = emailDao.queryEmailByTagId(mailtag,folder,users);
			}

			if(queryfrom.equals("family")) {
				
				mailtag.setTagname("亲友邮件");
				mailtag = mailTagDao.queryMailTagByName(mailtag);
				
				emailList = emailDao.queryEmailByTagId(mailtag,folder,users);
			}

			if(queryfrom.equals("student")) {
				
				mailtag.setTagname("同学邮件");
				mailtag = mailTagDao.queryMailTagByName(mailtag);
				
				emailList = emailDao.queryEmailByTagId(mailtag,folder,users);
			}

			if(queryfrom.equals("leisure")) {
				
				mailtag.setTagname("休闲邮件");
				mailtag = mailTagDao.queryMailTagByName(mailtag);
				
				emailList = emailDao.queryEmailByTagId(mailtag,folder,users);
			}

			if(queryfrom.equals("intresting")) {
				
				mailtag.setTagname("趣闻邮件");
				mailtag = mailTagDao.queryMailTagByName(mailtag);
				
				emailList = emailDao.queryEmailByTagId(mailtag,folder,users);
			}
		}else if(queryfrom.equals("all")) {
		
			emailList = emailDao.queryEmailByUnameAndFolder(users, folder);
		}
		
		List<EmailBean> emailBeanList = new ArrayList<EmailBean>();
		
		if(emailList != null) {
			for(Iterator<Email> i = emailList.iterator();i.hasNext();) {
				email = i.next();
				
				List<Attachment> attachmentList = attachmentDao.queryAttachmentByEmailId(email);
				
				List<CC> ccList = ccDao.queryCCByEmailId(email);
				
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
				if(attachmentList == null) {
					emailbean.setContainAttachment(false);
				}else {
					emailbean.setContainAttachment(true);
				}
				
				emailBeanList.add(emailbean);
				emailbean = null;
			}
		}
		
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

		request.getSession().setAttribute("receivePageNo", pageNo);
		request.getSession().setAttribute("receiveTotalPages", totalPages);
		
		request.getSession().setAttribute("emailBeanList", emailBeanListTemp);
		
		
		return mapping.findForward("success");
	}
	
	public void setFolderDao(FolderDao folderDao) {
		this.folderDao = folderDao;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}

	public void setCcDao(CCDao ccDao) {
		this.ccDao = ccDao;
	}

	public void setEmailbean(EmailBean emailbean) {
		this.emailbean = emailbean;
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
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public void setEmailDao(EmailDao emailDao) {
		this.emailDao = emailDao;
	}

}
