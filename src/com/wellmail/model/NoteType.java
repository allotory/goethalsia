package com.wellmail.model;

import java.util.HashSet;
import java.util.Set;

public class NoteType {

	//记事本类型id
	private int notetypeid;
	
	//记事本类型名称（1.普通记事 ， 2.代办纪事 ， 3.日常琐事 ）
	private String notetypename;
	
	//该记事本类型下所包含的文件数量
	private int containfilecount;
	
	//notebook set
	private Set<NoteBook> notebooks = new HashSet<NoteBook>();
	
	//user 外键
	private Users user;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Set<NoteBook> getNotebooks() {
		return notebooks;
	}

	public void setNotebooks(Set<NoteBook> notebooks) {
		this.notebooks = notebooks;
	}

	public int getNotetypeid() {
		return notetypeid;
	}

	public void setNotetypeid(int notetypeid) {
		this.notetypeid = notetypeid;
	}

	public String getNotetypename() {
		return notetypename;
	}

	public void setNotetypename(String notetypename) {
		this.notetypename = notetypename;
	}

	public int getContainfilecount() {
		return containfilecount;
	}

	public void setContainfilecount(int containfilecount) {
		this.containfilecount = containfilecount;
	}
}
