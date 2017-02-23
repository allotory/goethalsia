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
import com.wellmail.model.OtherMailBox;
import com.wellmail.model.OtherMailBoxType;
import com.wellmail.model.Users;

public class OtherMailBoxDeleteAction extends Action{
	
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
		
		
		//É¾³ý
		String delothermailbox[] = request.getParameterValues("mid");
		
		if(delothermailbox.length < 1) {
			
			request.setAttribute("flag", "checkboxmustchose");
			return mapping.findForward("failure");
		}else {
			
			for(int i = 0; i<delothermailbox.length; i++) {
				int uid = Integer.parseInt(delothermailbox[i]);
				
				othermailbox.setUid(uid);
				othermailbox = otherMailBoxDao.queryOtherMailBoxByUid(othermailbox);
				
				otherMailBoxDao.deleteOtherMailBox(othermailbox);
			}
		}
		
		
		

		//²éÑ¯
		
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
		
		//·ÖÒ³
		int pageSize = 7;
		
		int pageNo = 1;
		
		if(pageNo <= 0) {
			pageNo = 1;
		}
		
		int totalRecords = otherMailBoxBeanList.size();
		//System.out.println(totalRecords+"____totalRecords");
		
		int totalPages = totalRecords%pageSize == 0? totalRecords/pageSize : totalRecords/pageSize +1;
		
		if(pageNo > totalPages) {
			pageNo = totalPages;
		}
		
		int startPos = (pageNo - 1) * pageSize;
		//System.out.println(startPos+"____startPos");
		
		List<OtherMailBoxBean> otherMailBoxBeanListTemp = new ArrayList<OtherMailBoxBean>();
		
		//System.out.println(pageNo+"*****");
		for(int i = 0; i<pageNo-1; i++) {
			pageSize += 7;
		}
		//System.out.println(pageSize+"****");
		
		if(startPos >= totalRecords - totalRecords%pageSize) {
			pageSize = totalRecords%pageSize;
		}
		if(otherMailBoxBeanList.size() > 0) {
			for(int i = startPos; i < pageSize; i++) {
				otherMailBoxBeanListTemp.add(otherMailBoxBeanList.get(i));
				//System.out.println(articles.get(i)+"--------");
			}
		}

		request.getSession().setAttribute("othermailPageNo", pageNo);
		request.getSession().setAttribute("othermailTotalPages", totalPages);
		
		request.getSession().setAttribute("otherMailBoxBeanList", otherMailBoxBeanListTemp);
		
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
