package com.wellmail.model;

public class NoteBook {

	//记事本id
	private int noteid;
	
	//记事本标题
	private String notetitle;
	
	//记事本内容
	private String notecontent;
	
	//user 外键
	private Users user;
	
	//notetype 外键
	private NoteType notetype;

	public NoteType getNotetype() {
		return notetype;
	}

	public void setNotetype(NoteType notetype) {
		this.notetype = notetype;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getNoteid() {
		return noteid;
	}

	public void setNoteid(int noteid) {
		this.noteid = noteid;
	}

	public String getNotetitle() {
		return notetitle;
	}

	public void setNotetitle(String notetitle) {
		this.notetitle = notetitle;
	}

	public String getNotecontent() {
		return notecontent;
	}

	public void setNotecontent(String notecontent) {
		this.notecontent = notecontent;
	}
	
}
