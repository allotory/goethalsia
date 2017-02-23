package com.wellmail.form;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class NoteForm extends ActionForm {

	private String notetypename;
	
	private String notetitle;
	
	private String notecontent;
	
	private int notetypeid;
	
	private int noteid;
	
	private int pageNo;
	
	private int totalPages;

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

	public int getNoteid() {
		return noteid;
	}

	public void setNoteid(int noteid) {
		this.noteid = noteid;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	
}
