package com.wellmail.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wellmail.dao.ContactGroupDao;
import com.wellmail.model.ContactGroup;
import com.wellmail.model.Users;

public class ContactGroupDaoImpl extends HibernateDaoSupport implements
		ContactGroupDao {

	public void addContactGroup(ContactGroup contactgroup) {
		
		this.getHibernateTemplate().save(contactgroup);
	}

	@SuppressWarnings("unchecked")
	public List<ContactGroup> queryContactByUname(Users users) {
		List<ContactGroup> contactGroupList = this.getHibernateTemplate().find("from ContactGroup where username='"+users.getUsername() +"'");
		
		if(contactGroupList.size() > 0) {
			return contactGroupList;
			
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public ContactGroup queryContactGroupByGroupId(ContactGroup contactgroup) {
		List<ContactGroup> contactGroupList = this.getHibernateTemplate().find("from ContactGroup where groupid="+contactgroup.getGroupid());
		
		if(contactGroupList.size() == 1) {
			return contactGroupList.get(0);
			
		}
		return null;
	}

	public void delContactGroup(ContactGroup contactGroup) {

		this.getHibernateTemplate().delete(contactGroup);
	}

	public void modifyContactGroup(ContactGroup contactgroup) {

		this.getHibernateTemplate().update(contactgroup);
	}

}
