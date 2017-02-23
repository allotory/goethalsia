package com.wellmail.bean;

import com.wellmail.model.NoteBook;
import com.wellmail.model.NoteType;

public class NoteBookBean {

	private NoteType notetype;
	
	private NoteBook notebook;

	public NoteType getNotetype() {
		return notetype;
	}

	public void setNotetype(NoteType notetype) {
		this.notetype = notetype;
	}

	public NoteBook getNotebook() {
		return notebook;
	}

	public void setNotebook(NoteBook notebook) {
		this.notebook = notebook;
	}

}
