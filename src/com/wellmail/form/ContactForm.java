package com.wellmail.form;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class ContactForm extends ActionForm {

	private String groupname;
	
	private int groupid;
	
	private int groupid2;
	
	private int contactid;
	
	private String contactname;
	
	private String contactemail;

	public int getGroupid2() {
		return groupid2;
	}

	public void setGroupid2(int groupid2) {
		this.groupid2 = groupid2;
	}

	public int getContactid() {
		return contactid;
	}

	public void setContactid(int contactid) {
		this.contactid = contactid;
	}

	public String getContactname() {
		return contactname;
	}

	public void setContactname(String contactname) {
		this.contactname = contactname;
	}

	public String getContactemail() {
		return contactemail;
	}

	public void setContactemail(String contactemail) {
		this.contactemail = contactemail;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

}
