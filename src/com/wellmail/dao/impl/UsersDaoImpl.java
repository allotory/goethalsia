package com.wellmail.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wellmail.dao.UsersDao;
import com.wellmail.model.Users;

public class UsersDaoImpl extends HibernateDaoSupport implements UsersDao{

	public void addUser(Users user) {
		this.getHibernateTemplate().save(user);
	}

	@SuppressWarnings("unchecked")
	public boolean queryUserByNamePwd(Users user) {
		
		List<Users> usersList = this.getHibernateTemplate().find("from Users where username='"+ user.getUsername()+"' and pwdHash ='"+ user.getPwdHash()+"'");
		
		if(usersList.size() == 1) {
			return true;
			
		}else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public Users queryUserByName(Users user) {

		List<Users> usersList = this.getHibernateTemplate().find("from Users where username='"+ user.getUsername()+"'");
		
		Users users = new Users();
		if(usersList.size() == 1) {
			users = usersList.get(0);
			return users;
			
		}
		return null;
	}

	public void modifyUsers(Users users) {

		this.getHibernateTemplate().update(users);
	}

}
