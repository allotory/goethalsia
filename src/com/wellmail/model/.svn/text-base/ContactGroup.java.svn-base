package com.wellmail.model;

import java.util.HashSet;
import java.util.Set;

public class ContactGroup {

	//联系人分组id
	private int groupid;
	
	//联系人分组名称(1.朋友，2.亲人，3.同事，4.网友，5.老师，6.同学)
	private String groupname;
	
	//该分组下，所包含的用户数量
	private int containusercount;
	
	//contact set
	private Set<Contact> contacts = new HashSet<Contact>();
	
	//user外键
	private Users user;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public int getContainusercount() {
		return containusercount;
	}

	public void setContainusercount(int containusercount) {
		this.containusercount = containusercount;
	}
}
