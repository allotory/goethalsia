package com.wellmail.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wellmail.dao.FolderDao;
import com.wellmail.model.Folder;
import com.wellmail.model.Users;

public class FolderDaoImpl extends HibernateDaoSupport implements FolderDao {

	public void addFolder(Folder folder) {
		this.getHibernateTemplate().save(folder);
	}

	@SuppressWarnings("unchecked")
	public Folder queryFolderByUserAndFname(Users user, Folder folder) {

		//System.out.println(user.getUsername());
		List<Folder> folderList = this.getHibernateTemplate().find("from Folder where username='"+ user.getUsername() +"' and foldername = '" + folder.getFoldername()+"'");
		
		
		Folder folders = new Folder();
		folders = folderList.get(0);
		return folders;
	}

	public void modifyFolder(Folder folder) {

		this.getHibernateTemplate().update(folder);
	}

	@SuppressWarnings("unchecked")
	public Folder queryFolderByFolderId(Folder folder) {

		List<Folder> folderList = this.getHibernateTemplate().find("from Folder where folderid=" +folder.getFolderid());
		Folder folders = new Folder();
		if(folderList.size() > 0) {
			folders = folderList.get(0);
		}
		return folders;
	}

}
