package com.wellmail.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wellmail.dao.EmailSpaceDao;
import com.wellmail.model.EmailSpace;
import com.wellmail.model.Users;

public class EmailSpaceDaoImpl extends HibernateDaoSupport implements
		EmailSpaceDao {

	public void addEmailSpace(EmailSpace emailspace) {
		this.getHibernateTemplate().save(emailspace);
	}

	@SuppressWarnings("unchecked")
	public EmailSpace querySpaceByUser(Users users) {
		List<EmailSpace> spaceList = this.getHibernateTemplate().find("from EmailSpace where username='"+ users.getUsername() +"'");
		EmailSpace emailspace = null;
		
		if(spaceList.size() == 1){
			emailspace = (EmailSpace) spaceList.get(0);
		}
		return emailspace;
	}

	public void modifySpace(EmailSpace emailspace) {

		this.getHibernateTemplate().update(emailspace);
	}

}
