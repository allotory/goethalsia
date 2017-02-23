package com.wellmail.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wellmail.dao.BCCDao;
import com.wellmail.model.BCC;
import com.wellmail.model.Email;

public class BCCDaoImpl extends HibernateDaoSupport implements BCCDao {

	public void addBCC(BCC bcc) {

		this.getHibernateTemplate().save(bcc);
	}

	@SuppressWarnings("unchecked")
	public List<BCC> queryBCCByEmailId(Email email) {
		List<BCC> bccList = this.getHibernateTemplate().find("from BCC where emailid=" +email.getEmailid());
		if(bccList.size() > 0) {
			return bccList;
		}
		return null;
	}

	public void delBCC(BCC bccs) {

		this.getHibernateTemplate().delete(bccs);
	}

}
