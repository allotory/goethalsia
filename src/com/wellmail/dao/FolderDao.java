package com.wellmail.dao;

import com.wellmail.model.Folder;
import com.wellmail.model.Users;

public interface FolderDao {

	public void addFolder(Folder folder);
	
	public Folder queryFolderByUserAndFname(Users user, Folder folder);
	
	public void modifyFolder(Folder folder);
	
	public Folder queryFolderByFolderId(Folder folder);
	
}
