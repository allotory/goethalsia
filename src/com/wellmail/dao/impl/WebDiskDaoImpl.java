package com.wellmail.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wellmail.dao.WebDiskDao;
import com.wellmail.model.Users;
import com.wellmail.model.WebDisk;

public class WebDiskDaoImpl extends HibernateDaoSupport implements WebDiskDao {

	public void addWebDisk(WebDisk webdisk) {

		this.getHibernateTemplate().save(webdisk);
	}

	@SuppressWarnings("unchecked")
	public WebDisk queryWebDiskByUname(Users users) {
		List<WebDisk> webDiskList = this.getHibernateTemplate().find("from WebDisk where username='"+ users.getUsername() +"'");
		
		WebDisk webdisk = new WebDisk();
		webdisk = webDiskList.get(0);
		return webdisk;
	}

	public void modifyWebDisk(WebDisk webdisk) {

		this.getHibernateTemplate().update(webdisk);
	}

}
