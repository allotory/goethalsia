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

public class ReceiveChangeFlagAction extends Action {
	
	private EmailDao emailDao;
	private Email email;
	
	private MailTagDao mailTagDao;
	private MailTag mailtag;
	
	private PriorityDao priorityDao;
	private Priority priority;
	
	private Users users;
	private UsersDao usersDao;
	
	private FolderDao folderDao;
	private Folder folder;
	
	private AttachmentDao attachmentDao;

	private CCDao ccDao;
	
	private EmailBean emailbean;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String changeFlagMail[] = request.getParameterValues("mid");
		
		EmailForm ef = (EmailForm) form;
		String changeflag = ef.getChangeflag();
		System.out.println("changeflag"+changeflag);
		
		if(changeflag != "none") {
		
			for(int i = 0; i<changeFlagMail.length; i++) {
				int emailid = Integer.parseInt(changeFlagMail[i]);
				
				email = emailDao.queryEmailById(emailid);
				
				//邮件标记为已读或未读
				if(changeflag.equals("readtrue")) {
					email.setUnread(0);
					emailDao.modifyEmail(email);
				}else {
					email.setUnread(1);
					emailDao.modifyEmail(email);
				}
				
				//邮件标记为（优先级）紧急、普通、缓慢
				if(changeflag.equals("urgent")){
					priority.setPriorityname("紧急邮件");
					priority = priorityDao.queryPriorityByName(priority);
					
					email.setPriority(priority);
					emailDao.modifyEmail(email);
				}else if(changeflag.equals("commonpriority")){
					priority.setPriorityname("普通邮件");
					priority = priorityDao.queryPriorityByName(priority);
					
					email.setPriority(priority);
					emailDao.modifyEmail(email);
				}else if(changeflag.equals("slow")){
					priority.setPriorityname("缓慢邮件");
					priority = priorityDao.queryPriorityByName(priority);
					
					email.setPriority(priority);
					emailDao.modifyEmail(email);
				}
				
				
				//邮件标记为（标签）
				if(changeflag.equals("important")) {
					
					mailtag.setTagname("重要邮件");
					mailtag = mailTagDao.queryMailTagByName(mailtag);
					
					email.setMailtag(mailtag);
					emailDao.modifyEmail(email);
				}else if(changeflag.equals("company")) {
					
					mailtag.setTagname("公司邮件");
					mailtag = mailTagDao.queryMailTagByName(mailtag);
					
					email.setMailtag(mailtag);
					emailDao.modifyEmail(email);
				}else if(changeflag.equals("business")) {
					
					mailtag.setTagname("业务邮件");
					mailtag = mailTagDao.queryMailTagByName(mailtag);
					
					email.setMailtag(mailtag);
					emailDao.modifyEmail(email);
				}else if(changeflag.equals("information")) {
					
					mailtag.setTagname("资讯邮件");
					mailtag = mailTagDao.queryMailTagByName(mailtag);
					
					email.setMailtag(mailtag);
					emailDao.modifyEmail(email);
				}else if(changeflag.equals("family")) {
					
					mailtag.setTagname("亲友邮件");
					mailtag = mailTagDao.queryMailTagByName(mailtag);
					
					email.setMailtag(mailtag);
					emailDao.modifyEmail(email);
				}else if(changeflag.equals("student")) {
					
					mailtag.setTagname("同学邮件");
					mailtag = mailTagDao.queryMailTagByName(mailtag);
					
					email.setMailtag(mailtag);
					emailDao.modifyEmail(email);
				}else if(changeflag.equals("leisure")) {
					
					mailtag.setTagname("休闲邮件");
					mailtag = mailTagDao.queryMailTagByName(mailtag);
					
					email.setMailtag(mailtag);
					emailDao.modifyEmail(email);
				}else if(changeflag.equals("intresting")) {
					
					mailtag.setTagname("趣闻邮件");
					mailtag = mailTagDao.queryMailTagByName(mailtag);
					
					email.setMailtag(mailtag);
					emailDao.modifyEmail(email);
				}else if(changeflag.equals("commontag")) {
					
					mailtag.setTagname("普通邮件");
					mailtag = mailTagDao.queryMailTagByName(mailtag);
					
					email.setMailtag(mailtag);
					emailDao.modifyEmail(email);
				}
				
				
				//取消标记为
				if(changeflag.equals("cancel")) {
					
					//设置已读
					email.setUnread(0);
					
					//邮件标签设置为普通邮件
					mailtag.setTagname("普通邮件");
					mailtag = mailTagDao.queryMailTagByName(mailtag);
					email.setMailtag(mailtag);
					
					//邮件优先级设置为普通
					priority.setPriorityname("普通邮件");
					priority = priorityDao.queryPriorityByName(priority);
					email.setPriority(priority);
					
					emailDao.modifyEmail(email);
				}
				
			}
		}
		
		
		
		users = (Users)request.getSession().getAttribute("user");
		users = usersDao.queryUserByName(users);
		
		folder.setFoldername("收件箱");
		folder = folderDao.queryFolderByUserAndFname(users, folder);
		
		List<Email> emailList = emailDao.queryEmailByUnameAndFolder(users , folder);
		
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
				
				//email.setPriority(priority);
				//email.setMailtag(mailtag);
				
				//System.out.println("subject:"+email.getSubject());
				//System.out.println(""+email.getMailtag().getTagname());
				
				emailbean = new EmailBean();
				
				emailbean.setEmail(email);
				emailbean.setPriority(priority);
				emailbean.setMailtag(mailtag);
				System.out.println("Priority:"+priority.getPriorityid()+","+priority.getPriorityname());
				System.out.println("mailtag:"+mailtag.getTagid()+","+mailtag.getTagname());
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
		
		folder.setFoldername("收件箱");
		folder = folderDao.queryFolderByUserAndFname(users, folder);
		int unreadcount = emailDao.queryUnreadCount(users,folder);
		request.getSession().setAttribute("unreadcount", unreadcount);
		
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

	public void setUsers(Users users) {
		this.users = users;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
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
}
