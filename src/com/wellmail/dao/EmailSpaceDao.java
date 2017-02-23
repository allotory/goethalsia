package com.wellmail.dao;

import com.wellmail.model.EmailSpace;
import com.wellmail.model.Users;

public interface EmailSpaceDao {

	public void addEmailSpace(EmailSpace emailspace);
	
	public EmailSpace querySpaceByUser(Users users);
	
	public void modifySpace(EmailSpace emailspace);
}
