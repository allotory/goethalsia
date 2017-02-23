package com.wellmail.dao;

import com.wellmail.model.MailTag;

public interface MailTagDao {

	public void addMailTag(MailTag mailtag);
	
	public MailTag queryMailTagById(MailTag mailtag);
	
	public MailTag queryMailTagByName(MailTag mailtag);
	
}
