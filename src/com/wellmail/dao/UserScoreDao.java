package com.wellmail.dao;

import com.wellmail.model.UserScore;
import com.wellmail.model.Users;

public interface UserScoreDao {

	public void addUserScore(UserScore userscore );
	
	public UserScore queryScoreByUser(Users user);
	
	public void modifyScore(UserScore userscore);
}
