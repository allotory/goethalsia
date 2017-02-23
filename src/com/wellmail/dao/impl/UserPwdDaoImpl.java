package com.wellmail.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wellmail.dao.UserPwdDao;
import com.wellmail.model.UserPwd;

public class UserPwdDaoImpl extends HibernateDaoSupport implements UserPwdDao {

	public void addUserPwd(UserPwd userpwd) {
		this.getHibernateTemplate().save(userpwd);
	}

	@SuppressWarnings("unchecked")
	public UserPwd queryUserPwdByUname(String username) {
		List<UserPwd> userPwdList = this.getHibernateTemplate().find("from UserPwd where username='" +username+"'");
		if(userPwdList.size() == 1) {
			return userPwdList.get(0);
		}
		return null;
	}

}
