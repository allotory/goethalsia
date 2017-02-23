package com.wellmail.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wellmail.dao.AttachmentDao;
import com.wellmail.model.Attachment;
import com.wellmail.model.Email;

public class AttachmentDaoImpl extends HibernateDaoSupport implements
		AttachmentDao {

	public void addAttachment(Attachment attachment) {
		
		this.getHibernateTemplate().save(attachment);
	}

	@SuppressWarnings("unchecked")
	public List<Attachment> queryAttachmentByEmailId(Email email) {
		List<Attachment> attachmentList = this.getHibernateTemplate().find("from Attachment where emailid=" +email.getEmailid());
		if(attachmentList.size() > 0) {
			return attachmentList;
		}
		return null;
	}

	public void delAttachment(Attachment attachment) {

		this.getHibernateTemplate().delete(attachment);
	}
}
