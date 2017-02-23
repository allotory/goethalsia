package com.wellmail.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wellmail.dao.EmailDao;
import com.wellmail.dao.FolderDao;
import com.wellmail.dao.UsersDao;
import com.wellmail.model.Email;
import com.wellmail.model.Folder;
import com.wellmail.model.Users;

public class ClearSentBoxAction extends Action {

	private UsersDao usersDao;
	private Users users;
	
	private EmailDao emailDao;
	private Email email;
	
	private FolderDao folderDao;
	private Folder folder;
	
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		users = (Users) request.getSession().getAttribute("user");
		users = usersDao.queryUserByName(users);
		
		//取出所有被选中的Email的id
		String delmail[] = request.getParameterValues("mid");
		
		for(int i = 0; i<delmail.length; i++) {
			int emailid = Integer.parseInt(delmail[i]);
			
			folder.setFoldername("已删除");
			folder = folderDao.queryFolderByUserAndFname(users, folder);
			
			email = emailDao.queryEmailById(emailid);
			email.setFolder(folder);
			
			emailDao.modifyEmail(email);
	
			folder.setFoldername("已发送");
			folder = folderDao.queryFolderByUserAndFname(users, folder);
			folder.setFolderspace(folder.getFolderspace() - email.getMsgsize());
			folderDao.modifyFolder(folder);
			
			folder.setFoldername("已删除");
			folder = folderDao.queryFolderByUserAndFname(users, folder);
			folder.setFolderspace(folder.getFolderspace() + email.getMsgsize());
			folderDao.modifyFolder(folder);
		}
		
		return mapping.findForward("success");
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
