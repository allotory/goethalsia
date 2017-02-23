package com.wellmail.model;

public class WebFile {

	//网盘文件id
	private int fileid;
	
	//网盘文件名称
	private String filename;
	
	//文件大小
	private double filesize;
	
	//文件上传时间
	private String uploadtime;

	//user外键
	private Users user;
	
	//filetype 外键
	private FileType filetype;
	
	public FileType getFiletype() {
		return filetype;
	}

	public void setFiletype(FileType filetype) {
		this.filetype = filetype;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getFileid() {
		return fileid;
	}

	public void setFileid(int fileid) {
		this.fileid = fileid;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public double getFilesize() {
		return filesize;
	}

	public void setFilesize(double filesize) {
		this.filesize = filesize;
	}

	public String getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}
	
}
