package com.wellmail.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wellmail.dao.FileTypeDao;
import com.wellmail.model.FileType;
import com.wellmail.model.Users;

public class FileTypeDaoImpl extends HibernateDaoSupport implements FileTypeDao {

	public void addFileType(FileType filetype) {
		
		this.getHibernateTemplate().save(filetype);
	}

	@SuppressWarnings("unchecked")
	public List<FileType> queryFileTypeByUname(Users users) {
		List<FileType> fileTypeList = this.getHibernateTemplate().find("from FileType where username='"
				+ users.getUsername()+"'");
		
		if(fileTypeList.size() > 0) {
			return fileTypeList;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public FileType queryFileTypeByFTId(FileType filetype) {
		List<FileType> fileTypeList = this.getHibernateTemplate().find("from FileType where filetypeid="
				+ filetype.getFiletypeid());
		
		if(fileTypeList.size() == 1) {
			return fileTypeList.get(0);
		}
		return null;
	}

	public void delWebDiskFolder(FileType filetype) {

		this.getHibernateTemplate().delete(filetype);
	}

	public void modifyFileType(FileType filetype) {

		this.getHibernateTemplate().update(filetype);
	}

}
