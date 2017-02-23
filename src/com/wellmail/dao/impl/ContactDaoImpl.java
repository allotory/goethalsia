package com.wellmail.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wellmail.dao.ContactDao;
import com.wellmail.model.Contact;
import com.wellmail.model.ContactGroup;
import com.wellmail.model.Users;

public class ContactDaoImpl extends HibernateDaoSupport implements ContactDao {

	public void addContact(Contact contact) {

		this.getHibernateTemplate().save(contact);
	}

	@SuppressWarnings("unchecked")
	public List<Contact> queryContactByCGId(ContactGroup contactgroup) {
		List<Contact> contactList = this.getHibernateTemplate().find("from Contact where groupid="+ contactgroup.getGroupid());
			
		if(contactList.size() > 0) {
			return contactList;
			
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Contact> queryContactByUname(Users users) {
		List<Contact> contactList = this.getHibernateTemplate().find("from Contact where username='"+users.getUsername()+"'");
		
		if(contactList.size() > 0) {
			return contactList;
			
		}
		return null;
	}

	public void deleteContact(Contact contact) {
		
		this.getHibernateTemplate().delete(contact);
	}

	@SuppressWarnings("unchecked")
	public Contact queryContactByContactId(Contact contact) {
		List<Contact> contactList = this.getHibernateTemplate().find("from Contact where contactid="+contact.getContactid());
		
		if(contactList.size() == 1) {
			return contactList.get(0);
			
		}
		return null;
	}

	public void modifyContact(Contact contact) {

		this.getHibernateTemplate().update(contact);
	}

	@SuppressWarnings("unchecked")
	public Contact queryContactByEmail(Contact contact) {
		List<Contact> contactList = this.getHibernateTemplate().find("from Contact where contactemail='"+contact.getContactemail()+"'");
		
		if(contactList.size() > 0) {
			return contactList.get(0);
			
		}
		return null;
	}

}
