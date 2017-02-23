package com.wellmail.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wellmail.dao.EmailDao;
import com.wellmail.model.Email;
import com.wellmail.model.Folder;
import com.wellmail.model.MailTag;
import com.wellmail.model.Priority;
import com.wellmail.model.Users;

public class EmailDaoImpl extends HibernateDaoSupport implements EmailDao {

	public void addEmail(Email email) {

		this.getHibernateTemplate().save(email);
	}

	@SuppressWarnings("unchecked")
	public List<Email> queryEmailByUnameAndRecipAndFolder(Users users , Folder folder) {

		List<Email> emailList = this.getHibernateTemplate().find("from Email where username='"
				+ users.getUsername()+"' and recipients='"+users.getUsername()+"' and folderid=" 
				+ folder.getFolderid());
		
		if(emailList.size() > 0) {
			return emailList;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public int queryUnreadCount(Users users,Folder folder) {
		
		List<Email> emailList = this.getHibernateTemplate().find("from Email where username='"
				+ users.getUsername()+"' and folderid="+folder.getFolderid()+" and unread=1");
		
		return emailList.size();
	}

	@SuppressWarnings("unchecked")
	public Email queryEmailById(int emailid) {
		List<Email> emailList = this.getHibernateTemplate().find("from Email where emailid="+emailid);
		
		Email email = new Email();
		if(emailList.size() == 1) {
			
			email = emailList.get(0);
			return email;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Email> queryEmailByUnameAndFolder(Users users, Folder folder) {
		List<Email> emailList = this.getHibernateTemplate().find("from Email where username='"
				+ users.getUsername()+"' and folderid="+folder.getFolderid());
		if(emailList.size() > 0) {
			return emailList;
		}
		return null;
	}

	public void modifyEmail(Email email) {

		this.getHibernateTemplate().update(email);
	}

	public void delEmail(Email email) {

		this.getHibernateTemplate().delete(email);
	}

	@SuppressWarnings("unchecked")
	public List<Email> queryEmailByPriority(Priority priority, Folder folder ,Users users) {
		List<Email> emailList = this.getHibernateTemplate().find("from Email where priorityid="+priority.getPriorityid()+" and username='"+users.getUsername()+"' and folderid=" + folder.getFolderid());
		if(emailList.size() > 0) {
			return emailList;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Email> queryEmailByRead(Users users , Folder folder) {
		List<Email> emailList = this.getHibernateTemplate().find("from Email where unread=0 and username='"+users.getUsername()+"' and folderid=" + folder.getFolderid());
		
		return emailList;
	}

	@SuppressWarnings("unchecked")
	public List<Email> queryEmailByUnread(Users users , Folder folder) {
		List<Email> emailList = this.getHibernateTemplate().find("from Email where unread=1 and username='"+users.getUsername()+"' and folderid=" + folder.getFolderid());
		
		return emailList;
	}

	@SuppressWarnings("unchecked")
	public List<Email> queryEmailByTagId(MailTag mailtag,Folder folder ,Users users) {
		List<Email> emailList = this.getHibernateTemplate().find("from Email where tagid="+mailtag.getTagid()+" and username='"+users.getUsername()+"' and folderid="+ folder.getFolderid());
		if(emailList.size() > 0) {
			return emailList;
		}
		return null;
	}

}
