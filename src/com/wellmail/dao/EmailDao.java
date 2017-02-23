package com.wellmail.dao;

import java.util.List;

import com.wellmail.model.Email;
import com.wellmail.model.Folder;
import com.wellmail.model.MailTag;
import com.wellmail.model.Priority;
import com.wellmail.model.Users;

public interface EmailDao {

	public void addEmail(Email email);
	
	public List<Email> queryEmailByUnameAndRecipAndFolder(Users users ,Folder Folder);
	
	public int queryUnreadCount(Users users,Folder folder);
	
	public Email queryEmailById(int emailid);
	
	public List<Email> queryEmailByUnameAndFolder(Users users,Folder folder);
	
	public void modifyEmail(Email email);
	
	public void delEmail(Email email);
	
	public List<Email> queryEmailByPriority(Priority priority,Folder folder ,Users users);
	
	public List<Email> queryEmailByUnread(Users users , Folder folder);
	
	public List<Email> queryEmailByRead(Users users , Folder folder);
	
	public List<Email> queryEmailByTagId(MailTag mailtag ,Folder folder ,Users users);
}
