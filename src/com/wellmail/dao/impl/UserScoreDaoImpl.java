package com.wellmail.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wellmail.dao.UserScoreDao;
import com.wellmail.model.UserScore;
import com.wellmail.model.Users;

public class UserScoreDaoImpl extends HibernateDaoSupport implements
		UserScoreDao {

	public void addUserScore(UserScore userscore) {
		
		this.getHibernateTemplate().save(userscore);
	}

	@SuppressWarnings("unchecked")
	public UserScore queryScoreByUser(Users user) {
		
		List<UserScore> scoreList = this.getHibernateTemplate().find("from UserScore where username='"+ user.getUsername() +"'");
		UserScore userscore = null;
		
		if(scoreList.size() == 1){
			userscore = (UserScore) scoreList.get(0);
		}
		return userscore;
	}

	public void modifyScore(UserScore userscore) {

		this.getHibernateTemplate().update(userscore);
	}

}
