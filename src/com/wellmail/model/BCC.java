package com.wellmail.model;

public class BCC {

	//密送人id
	private int bccid;
	
	//密送人姓名
	private String bccname;
	
	//Email 外键id
	private Email email;

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public int getBccid() {
		return bccid;
	}

	public void setBccid(int bccid) {
		this.bccid = bccid;
	}

	public String getBccname() {
		return bccname;
	}

	public void setBccname(String bccname) {
		this.bccname = bccname;
	}
	
}
