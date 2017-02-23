package com.wellmail.bean;

import java.util.Date;

public class OtherMailBean {

	private String from;
	
	private String subject;
	
	private Date sentdate;
	
	private String path;
	
	private boolean isNew;
	
	private boolean hasAttach;

	public boolean isHasAttach() {
		return hasAttach;
	}

	public void setHasAttach(boolean hasAttach) {
		this.hasAttach = hasAttach;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}


	public Date getSentdate() {
		return sentdate;
	}

	public void setSentdate(Date sentdate) {
		this.sentdate = sentdate;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
