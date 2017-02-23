package com.wellmail.dao;

import java.util.List;

import com.wellmail.model.FileType;
import com.wellmail.model.Users;
import com.wellmail.model.WebFile;

public interface WebFileDao {

	public List<WebFile> queryWebFileByUnameAndFileType(Users users, FileType fileType);
	
	public void delWebFile(WebFile webfile);
	
	public WebFile queryWebFileById(WebFile webfile);
}
