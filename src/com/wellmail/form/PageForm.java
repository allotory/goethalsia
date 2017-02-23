package com.wellmail.form;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class PageForm extends ActionForm {

	private int pageNo;
	
	private int totalPages;

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
