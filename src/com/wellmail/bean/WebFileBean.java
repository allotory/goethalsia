package com.wellmail.bean;

import java.util.List;

import com.wellmail.model.FileType;
import com.wellmail.model.WebFile;

public class WebFileBean {

	private FileType filetype;
	
	private List<WebFile> webFileList;

	public FileType getFiletype() {
		return filetype;
	}

	public void setFiletype(FileType filetype) {
		this.filetype = filetype;
	}

	public List<WebFile> getWebFileList() {
		return webFileList;
	}

	public void setWebFileList(List<WebFile> webFileList) {
		this.webFileList = webFileList;
	}
}
