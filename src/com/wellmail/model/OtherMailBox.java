package com.wellmail.model;

public class OtherMailBox {

	private int uid;
	
	private String uname;
	
	private String upass;
	
	private Users users;
	
	private OtherMailBoxType othermailboxtype;

	public OtherMailBoxType getOthermailboxtype() {
		return othermailboxtype;
	}

	public void setOthermailboxtype(OtherMailBoxType othermailboxtype) {
		this.othermailboxtype = othermailboxtype;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpass() {
		return upass;
	}

	public void setUpass(String upass) {
		this.upass = upass;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	
}
