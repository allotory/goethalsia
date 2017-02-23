package com.wellmail.init;

import com.wellmail.model.FileType;
import com.wellmail.model.Users;

public class FileTypeInit {

	public FileType initFileType(FileType filetype,String filetypename,Users user){
		
		filetype.setFiletypename(filetypename);
		filetype.setContainfilecount(0);
		filetype.setUser(user);
		
		return filetype;
	}
}
