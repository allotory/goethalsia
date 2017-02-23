package com.wellmail.dao;

import com.wellmail.model.Users;

public interface UsersDao {

	public void addUser(Users user);
	
	public boolean queryUserByNamePwd(Users user);
	
	public Users queryUserByName(Users user);
	
	public void modifyUsers(Users users);
}
