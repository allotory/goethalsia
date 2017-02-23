package com.wellmail.dao;

import java.util.List;

import com.wellmail.model.FileType;
import com.wellmail.model.Users;

public interface FileTypeDao {

	public void addFileType(FileType filetype);
	
	public List<FileType> queryFileTypeByUname(Users users);
	
	public FileType queryFileTypeByFTId(FileType filetype);
	
	public void delWebDiskFolder(FileType filetype);
	
	public void modifyFileType(FileType filetype);
}
