package com.wellmail.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wellmail.dao.UserPwdDao;
import com.wellmail.form.UsersForm;
import com.wellmail.model.UserPwd;

public class UserQuesAction extends Action {
	
	private UserPwdDao userPwdDao;
	
	private UserPwd userpwd;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		UsersForm uf = (UsersForm)form;
		String username = uf.getUsername();
		
		userpwd = userPwdDao.queryUserPwdByUname(username+"@mymail.com");
		request.getSession().setAttribute("userpwd", userpwd);
		
		return mapping.findForward("success");
	}

	public void setUserPwdDao(UserPwdDao userPwdDao) {
		this.userPwdDao = userPwdDao;
	}

	public void setUserpwd(UserPwd userpwd) {
		this.userpwd = userpwd;
	}
}
