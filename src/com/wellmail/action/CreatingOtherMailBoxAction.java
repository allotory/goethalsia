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

import com.wellmail.bean.OtherMailBoxBean;
import com.wellmail.dao.OtherMailBoxDao;
import com.wellmail.dao.OtherMailBoxTypeDao;
import com.wellmail.dao.UsersDao;
import com.wellmail.form.OtherMailBoxForm;
import com.wellmail.model.OtherMailBox;
import com.wellmail.model.OtherMailBoxType;
import com.wellmail.model.Users;

public class CreatingOtherMailBoxAction extends Action{
	
	private UsersDao usersDao;
	private Users users;
	
	private OtherMailBoxTypeDao otherMailBoxTypeDao;
	private OtherMailBoxType othermailboxtype;
	
	private OtherMailBoxDao otherMailBoxDao;
	private OtherMailBox othermailbox;
	
	private OtherMailBoxBean othermailboxbean;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		users = (Users) request.getSession().getAttribute("user");
		users = usersDao.queryUserByName(users);
		

		OtherMailBoxForm ombf = (OtherMailBoxForm)form;
		
		int tid = ombf.getTid();
		String uname = ombf.getUname();
		String upass = ombf.getUpass();
		
		
		othermailboxtype.setTid(tid);
		othermailboxtype = otherMailBoxTypeDao.queryOtherMailBoxTypeByTid(othermailboxtype);
		
		othermailbox.setUname(uname);
		othermailbox.setUpass(upass);
		othermailbox.setUsers(users);
		othermailbox.setOthermailboxtype(othermailboxtype);
		
		otherMailBoxDao.addOtherMailBox(othermailbox);
		
		//≤È—Ø
		
		List<OtherMailBox> otherMailBoxList = otherMailBoxDao.queryAllOtheraMailBox(users);
		
		List<OtherMailBoxBean> otherMailBoxBeanList = new ArrayList<OtherMailBoxBean>();
		
		if( otherMailBoxList != null) {
			for(Iterator<OtherMailBox> i = otherMailBoxList.iterator();i.hasNext();) {
				
				othermailbox = i.next();
				
				othermailboxtype.setTid(othermailbox.getOthermailboxtype().getTid());
				othermailboxtype = otherMailBoxTypeDao.queryOtherMailBoxTypeByTid(othermailboxtype);
				
				othermailboxbean = new OtherMailBoxBean();
				othermailboxbean.setOthermailbox(othermailbox);
				othermailboxbean.setOthermailboxtype(othermailboxtype);
				
				otherMailBoxBeanList.add(othermailboxbean);
				othermailboxbean = null;
			}
		}
		
		request.getSession().setAttribute("otherMailBoxBeanList", otherMailBoxBeanList);
		
		return mapping.findForward("success");
	}

	public void setOthermailboxbean(OtherMailBoxBean othermailboxbean) {
		this.othermailboxbean = othermailboxbean;
	}

	public void setOthermailboxtype(OtherMailBoxType othermailboxtype) {
		this.othermailboxtype = othermailboxtype;
	}

	public void setOtherMailBoxDao(OtherMailBoxDao otherMailBoxDao) {
		this.otherMailBoxDao = otherMailBoxDao;
	}

	public void setOthermailbox(OtherMailBox othermailbox) {
		this.othermailbox = othermailbox;
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
