package com.wellmail.model;

public class WebDisk {

	//网盘id
	private int webspaceid;
	
	//网盘总空间大小
	private double webspace;
	
	//网盘剩余空间大小
	private double wsleft;
	
	//网盘文件数量
	private int filecount;
	
	//用户外键
	private Users user;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getWebspaceid() {
		return webspaceid;
	}

	public void setWebspaceid(int webspaceid) {
		this.webspaceid = webspaceid;
	}

	public double getWebspace() {
		return webspace;
	}

	public void setWebspace(double webspace) {
		this.webspace = webspace;
	}

	public double getWsleft() {
		return wsleft;
	}

	public void setWsleft(double wsleft) {
		this.wsleft = wsleft;
	}

	public int getFilecount() {
		return filecount;
	}

	public void setFilecount(int filecount) {
		this.filecount = filecount;
	}
}
