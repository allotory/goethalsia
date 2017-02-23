package com.wellmail.dao;

import java.util.List;

import com.wellmail.model.Attachment;
import com.wellmail.model.Email;

public interface AttachmentDao {

	public void addAttachment(Attachment attachment);
	
	public List<Attachment> queryAttachmentByEmailId(Email email);
	
	public void delAttachment(Attachment attachment);
	
}
