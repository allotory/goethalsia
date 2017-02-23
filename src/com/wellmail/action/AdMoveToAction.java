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
import com.wellmail.form.EmailForm;
import com.wellmail.model.Attachment;
import com.wellmail.model.BCC;
import com.wellmail.model.CC;
import com.wellmail.model.Email;
import com.wellmail.model.Folder;
import com.wellmail.model.MailTag;
import com.wellmail.model.Priority;
import com.wellmail.model.Users;

public class AdMoveToAction extends Action {
	
	
	private UsersDao usersDao;
	private Users users;
	
	private EmailDao emailDao;
	private Email email;
	
	private FolderDao folderDao;
	private Folder folder;
	
	private CCDao ccDao;
	
	private BCCDao bccDao;
	
	private MailTagDao mailTagDao;
	private MailTag mailtag;
	
	private PriorityDao priorityDao;
	private Priority priority;
	
	private EmailBean emailbean;
	
	private AttachmentDao attachmentDao;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		users = (Users) request.getSession().getAttribute("user");
		users = usersDao.queryUserByName(users);
		
		String moveToMail[] = request.getParameterValues("mid");
		
		EmailForm ef = (EmailForm) form;
		String moveto = ef.getMoveto();
		
		if(!moveto.equals("none")) {
			
			for(int i = 0; i<moveToMail.length; i++) {
				int emailid = Integer.parseInt(moveToMail[i]);
				
				email = emailDao.queryEmailById(emailid);
				
				if(moveto.equals("receivebox")) {
					
					folder.setFolderid(email.getFolder().getFolderid());
					folder = folderDao.queryFolderByFolderId(folder);
					
					if(!folder.getFoldername().equals("收件箱")) {
						
						folder.setFoldername("收件箱");
						folder.setFolderspace(folder.getFolderspace() - email.getMsgsize());
						folder.setMailcount(folder.getMailcount() +1);
						folder = folderDao.queryFolderByUserAndFname(users, folder);
						
						email.setFolder(folder);
						emailDao.modifyEmail(email);
					}
				}
				
				if(moveto.equals("deletedbox")) {
					
					folder.setFolderid(email.getFolder().getFolderid());
					folder = folderDao.queryFolderByFolderId(folder);
					
					if(!folder.getFoldername().equals("已删除")) {
						
						folder.setFoldername("已删除");
						folder.setFolderspace(folder.getFolderspace() - email.getMsgsize());
						folder.setMailcount(folder.getMailcount() +1);
						folder = folderDao.queryFolderByUserAndFname(users, folder);
						
						email.setFolder(folder);
						emailDao.modifyEmail(email);
					}
				}

				if(moveto.equals("scriptbox")) {
					
					folder.setFolderid(email.getFolder().getFolderid());
					folder = folderDao.queryFolderByFolderId(folder);
					
					if(!folder.getFoldername().equals("草稿箱")) {
						
						folder.setFoldername("草稿箱");
						folder.setFolderspace(folder.getFolderspace() - email.getMsgsize());
						folder.setMailcount(folder.getMailcount() +1);
						folder = folderDao.queryFolderByUserAndFname(users, folder);
						
						email.setFolder(folder);
						emailDao.modifyEmail(email);
					}
				}
				
				if(moveto.equals("sendedbox")) {
					
					folder.setFolderid(email.getFolder().getFolderid());
					folder = folderDao.queryFolderByFolderId(folder);
					
					if(!folder.getFoldername().equals("已发送")) {
						
						folder.setFoldername("已发送");
						folder.setFolderspace(folder.getFolderspace() - email.getMsgsize());
						folder.setMailcount(folder.getMailcount() +1);
						folder = folderDao.queryFolderByUserAndFname(users, folder);
						
						email.setFolder(folder);
						emailDao.modifyEmail(email);
					}
				}

				if(moveto.equals("adbox")) {
					
					folder.setFolderid(email.getFolder().getFolderid());
					folder = folderDao.queryFolderByFolderId(folder);
					
					if(!folder.getFoldername().equals("广告邮件")) {
						
						folder.setFoldername("广告邮件");
						folder.setFolderspace(folder.getFolderspace() - email.getMsgsize());
						folder.setMailcount(folder.getMailcount() +1);
						folder = folderDao.queryFolderByUserAndFname(users, folder);
						
						email.setFolder(folder);
						emailDao.modifyEmail(email);
					}
				}
			}
		}

		users = (Users)request.getSession().getAttribute("user");
		users = usersDao.queryUserByName(users);
		
		folder.setFoldername("广告邮件");
		folder = folderDao.queryFolderByUserAndFname(users, folder);
		
		List<Email> emailList = emailDao.queryEmailByUnameAndFolder(users, folder);
		
		List<EmailBean> emailBeanList = new ArrayList<EmailBean>();
		
		if(emailList != null) {
			for(Iterator<Email> i = emailList.iterator();i.hasNext();) {
				email = i.next();
				
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

	public void setCcDao(CCDao ccDao) {
		this.ccDao = ccDao;
	}

	public void setBccDao(BCCDao bccDao) {
		this.bccDao = bccDao;
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

	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
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

	public void setEmail(Email email) {
		this.email = email;
	}

	public void setFolderDao(FolderDao folderDao) {
		this.folderDao = folderDao;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	
}
