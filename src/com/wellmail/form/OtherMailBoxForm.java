package com.wellmail.form;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class OtherMailBoxForm extends ActionForm {

	private int tid;
	
	private int uid;
	
	private String uname;
	
	private String upass;

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
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

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	
}
