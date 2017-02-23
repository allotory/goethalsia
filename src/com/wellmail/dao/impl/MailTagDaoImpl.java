package com.wellmail.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wellmail.dao.MailTagDao;
import com.wellmail.model.MailTag;

public class MailTagDaoImpl extends HibernateDaoSupport implements MailTagDao {

	public void addMailTag(MailTag mailtag) {

		this.getHibernateTemplate().save(mailtag);
	}

	@SuppressWarnings("unchecked")
	public MailTag queryMailTagById(MailTag mailtag) {

		List<MailTag> mailTagList = this.getHibernateTemplate().find("from MailTag where tagid="+ mailtag.getTagid());
		
		MailTag mailtags = new MailTag();
		if(mailTagList.size() == 1) {
			mailtags = mailTagList.get(0);
			return mailtags;
			
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public MailTag queryMailTagByName(MailTag mailtag) {

		List<MailTag> mailTagList = this.getHibernateTemplate().find("from MailTag where tagname='"+ mailtag.getTagname()+"'");
		
		MailTag mailtags = new MailTag();
		if(mailTagList.size() == 1) {
			mailtags = mailTagList.get(0);
			return mailtags;
			
		}
		return null;
	}

}
