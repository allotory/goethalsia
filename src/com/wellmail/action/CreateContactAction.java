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

import com.wellmail.bean.ContactBean;
import com.wellmail.dao.ContactDao;
import com.wellmail.dao.ContactGroupDao;
import com.wellmail.dao.UsersDao;
import com.wellmail.form.ContactForm;
import com.wellmail.model.Contact;
import com.wellmail.model.ContactGroup;
import com.wellmail.model.Users;

public class CreateContactAction extends Action {
	
	private UsersDao usersDao;
	private Users users;
	
	private ContactGroupDao contactGroupDao;
	private ContactGroup contactgroup;
	
	private ContactDao contactDao;
	private Contact contact;
	
	private ContactBean contactbean;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		users = (Users)request.getSession().getAttribute("user");
		users = usersDao.queryUserByName(users);
		
		//新建联系人
		ContactForm cf = (ContactForm)form;
		int groupid = cf.getGroupid();
		String contactname = new String(cf.getContactname().getBytes("ISO8859-1"),"GB18030");
		String contactemail = cf.getContactemail();
		
		contactgroup.setGroupid(groupid);
		contactgroup = contactGroupDao.queryContactGroupByGroupId(contactgroup);
		contactgroup.setContainusercount(contactgroup.getContainusercount() + 1);
		contactGroupDao.modifyContactGroup(contactgroup);
		
		contact.setContactemail(contactemail);
		contact.setContactname(contactname);
		contact.setContactgroup(contactgroup);
		contact.setUser(users);
		
		contactDao.addContact(contact);
		
		request.setAttribute("groupid", groupid);
		
		//查询
		List<ContactGroup> contactGroupList = contactGroupDao.queryContactByUname(users);
		
		List<ContactBean> contactBeanList = new ArrayList<ContactBean>();
		
		if(contactGroupList != null) {
			
			for(Iterator<ContactGroup> i = contactGroupList.iterator(); i.hasNext();) {
				contactgroup = i.next();
				
				List<Contact> contactList = contactDao.queryContactByCGId(contactgroup);
				
				contactbean = new ContactBean();
				contactbean.setContactgroup(contactgroup);
				contactbean.setContactList(contactList);
				contactBeanList.add(contactbean);
				
				contactbean = null;
			}
		}
		request.getSession().setAttribute("contactBeanList", contactBeanList);
		
		List<Contact> contactList = contactDao.queryContactByUname(users);
		request.getSession().setAttribute("contactList", contactList);
		
		return mapping.findForward("success");
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public void setContactbean(ContactBean contactbean) {
		this.contactbean = contactbean;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public void setContactGroupDao(ContactGroupDao contactGroupDao) {
		this.contactGroupDao = contactGroupDao;
	}

	public void setContactgroup(ContactGroup contactgroup) {
		this.contactgroup = contactgroup;
	}

	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}
}
