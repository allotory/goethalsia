package com.wellmail.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wellmail.dao.ContactDao;
import com.wellmail.dao.ContactGroupDao;
import com.wellmail.dao.UsersDao;
import com.wellmail.form.ContactForm;
import com.wellmail.model.Contact;
import com.wellmail.model.ContactGroup;
import com.wellmail.model.Users;

public class MailAddToContactAction extends Action {
	
	private UsersDao usersDao;
	private Users users;
	
	private ContactGroupDao contactGroupDao;
	
	private ContactDao contactDao;
	private Contact contact;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println();
		
		users = (Users)request.getSession().getAttribute("user");
		users = usersDao.queryUserByName(users);
		
		ContactForm cf = (ContactForm)form;
		String contactemail = cf.getContactemail();
		
		contact.setContactemail(contactemail);
		contact = contactDao.queryContactByEmail(contact);
		
		if(contact != null) {
			
			request.setAttribute("flag", "contactalreadyexist");
			return mapping.findForward("failure");
		}
		
		List<ContactGroup> contactGroupList = contactGroupDao.queryContactByUname(users);
		
		request.setAttribute("contactemail", contactemail);
		request.setAttribute("conactgrouplist",contactGroupList);
		
		return mapping.findForward("success");
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public void setContactGroupDao(ContactGroupDao contactGroupDao) {
		this.contactGroupDao = contactGroupDao;
	}

	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}
}
