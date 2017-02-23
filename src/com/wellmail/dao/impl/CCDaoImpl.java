package com.wellmail.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wellmail.dao.CCDao;
import com.wellmail.model.CC;
import com.wellmail.model.Email;

public class CCDaoImpl extends HibernateDaoSupport implements CCDao {

	public void addCC(CC cc) {
		
		this.getHibernateTemplate().save(cc);
	}

	@SuppressWarnings("unchecked")
	public List<CC> queryCCByEmailId(Email email) {
		List<CC> ccList = this.getHibernateTemplate().find("from CC where emailid=" +email.getEmailid());
		if(ccList.size() > 0) {
			return ccList;
		}
		return null;
	}

	public void delCC(CC ccs) {

		this.getHibernateTemplate().delete(ccs);
	}

}
