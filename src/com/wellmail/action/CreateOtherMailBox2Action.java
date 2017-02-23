package com.wellmail.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wellmail.dao.OtherMailBoxTypeDao;
import com.wellmail.dao.UsersDao;
import com.wellmail.model.OtherMailBoxType;
import com.wellmail.model.Users;

public class CreateOtherMailBox2Action extends Action{
	
	private UsersDao usersDao;
	private Users users;
	
	private OtherMailBoxTypeDao otherMailBoxTypeDao;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		users = (Users) request.getSession().getAttribute("user");
		users = usersDao.queryUserByName(users);
		
		List<OtherMailBoxType> otherMailBoxTypeList = otherMailBoxTypeDao.queryAllOtherMailBoxType();
		
		request.getSession().setAttribute("otherMailBoxTypeList", otherMailBoxTypeList);
		
		return mapping.findForward("success");
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void setUsers(Users users) {
		this.users = users;
	}


	public void setOtherMailBoxTypeDao(OtherMailBoxTypeDao otherMailBoxTypeDao) {
		this.otherMailBoxTypeDao = otherMailBoxTypeDao;
	}
}
