package com.wellmail.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wellmail.dao.WebFileDao;
import com.wellmail.model.FileType;
import com.wellmail.model.Users;
import com.wellmail.model.WebFile;

public class WebFileDaoImpl extends HibernateDaoSupport implements WebFileDao {

	@SuppressWarnings("unchecked")
	public List<WebFile> queryWebFileByUnameAndFileType(Users users,
			FileType fileType) {
		
		List<WebFile> webFileList = this.getHibernateTemplate().find("from WebFile where username='"
				+ users.getUsername()+"' and filetypeid="+fileType.getFiletypeid());
		
		if(webFileList.size() > 0) {
			return webFileList;
		}
		
		return null;
	}

	public void delWebFile(WebFile webfile) {

		this.getHibernateTemplate().delete(webfile);
	}

	@SuppressWarnings("unchecked")
	public WebFile queryWebFileById(WebFile webfile) {
		List<WebFile> webFileList = this.getHibernateTemplate().find("from WebFile where fileid="
				+ webfile.getFileid());
		
		if(webFileList.size() == 1) {
			return webFileList.get(0);
		}
		
		return null;
	}

}
