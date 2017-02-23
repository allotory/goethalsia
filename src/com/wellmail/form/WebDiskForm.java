package com.wellmail.form;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class WebDiskForm extends ActionForm {

	private String foldername;
	
	private int filetypeid;

	public int getFiletypeid() {
		return filetypeid;
	}

	public void setFiletypeid(int filetypeid) {
		this.filetypeid = filetypeid;
	}

	public String getFoldername() {
		return foldername;
	}

	public void setFoldername(String foldername) {
		this.foldername = foldername;
	}
	
}
