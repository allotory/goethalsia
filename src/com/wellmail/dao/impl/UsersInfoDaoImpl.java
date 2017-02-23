package com.wellmail.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wellmail.dao.UsersInfoDao;
import com.wellmail.model.UsersInfo;

public class UsersInfoDaoImpl extends HibernateDaoSupport implements
		UsersInfoDao {

	public void addUsersInfo(UsersInfo usersinfo) {

		this.getHibernateTemplate().save(usersinfo);
	}

}
