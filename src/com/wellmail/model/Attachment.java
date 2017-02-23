package com.wellmail.model;

public class Attachment {

	//附件id
	private int attachmentid;
	
	//附件路径
	private String attachmentpath;

	//附件名称
	private String attachmentname;
	
	//附件大小
	private double attachmentsize;
	
	//emailid外键
	private Email email;
	
	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public String getAttachmentname() {
		return attachmentname;
	}

	public void setAttachmentname(String attachmentname) {
		this.attachmentname = attachmentname;
	}

	public double getAttachmentsize() {
		return attachmentsize;
	}

	public void setAttachmentsize(double attachmentsize) {
		this.attachmentsize = attachmentsize;
	}

	public int getAttachmentid() {
		return attachmentid;
	}

	public void setAttachmentid(int attachmentid) {
		this.attachmentid = attachmentid;
	}

	public String getAttachmentpath() {
		return attachmentpath;
	}

	public void setAttachmentpath(String attachmentpath) {
		this.attachmentpath = attachmentpath;
	}
}
