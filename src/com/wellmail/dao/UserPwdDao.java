package com.wellmail.dao;

import com.wellmail.model.UserPwd;

public interface UserPwdDao {

	public void addUserPwd(UserPwd userpwd);
	
	public UserPwd queryUserPwdByUname(String username);
}
