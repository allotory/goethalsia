package com.wellmail.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wellmail.dao.OtherMailBoxDao;
import com.wellmail.model.OtherMailBox;
import com.wellmail.model.Users;

public class OtherMailBoxDaoImpl extends HibernateDaoSupport implements
		OtherMailBoxDao {

	public void addOtherMailBox(OtherMailBox othermailbox) {

		this.getHibernateTemplate().save(othermailbox);
	}

	@SuppressWarnings("unchecked")
	public List<OtherMailBox> queryAllOtheraMailBox(Users users) {

		List<OtherMailBox> OtherMailBoxList = this.getHibernateTemplate().find("from OtherMailBox where username='"+users.getUsername()+"'");
		
		if(OtherMailBoxList.size() > 0) {
			return OtherMailBoxList;
			
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public OtherMailBox queryOtherMailBoxByUid(OtherMailBox othermailbox) {

		List<OtherMailBox> OtherMailBoxList = this.getHibernateTemplate().find("from OtherMailBox where uid=" + othermailbox.getUid());
		
		if(OtherMailBoxList.size() == 1) {
			return OtherMailBoxList.get(0);
			
		}
		return null;
	}

	public void deleteOtherMailBox(OtherMailBox othermailbox) {

		this.getHibernateTemplate().delete(othermailbox);
	}

}
