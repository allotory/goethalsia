package com.wellmail.dao;

import com.wellmail.model.Users;
import com.wellmail.model.WebDisk;

public interface WebDiskDao {

	public void addWebDisk(WebDisk webdisk);
	
	public WebDisk queryWebDiskByUname(Users users);
	
	public void modifyWebDisk(WebDisk webdisk);
}
