package com.wellmail.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.james.security.DigestUtil;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wellmail.dao.UsersDao;
import com.wellmail.form.UsersForm;
import com.wellmail.model.Users;

public class ModifyUserAction extends Action {
	
	private UsersDao usersDao;
	
	private Users users;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		UsersForm uf = (UsersForm)form;
		if(uf.getPwdHash().equals(uf.getPwdHashConfirm())) {
			
			String username = uf.getUsername();
			//System.out.println(username+"\\\\");
			users.setUsername(username);
			users = usersDao.queryUserByName(users);
			//System.out.println(uf.getPwdHash());
			users.setPwdHash(DigestUtil.digestString(uf.getPwdHash(),"SHA"));
			usersDao.modifyUsers(users);
			

			request.setAttribute("flag", "gotologin");
			return mapping.findForward("success");
			
		}else {
			
			//两次密码不一致
			request.setAttribute("flag", "passwordnotequal");
			return mapping.findForward("failure");
		}
		
		
		
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
}
