package com.wellmail.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wellmail.dao.NoteBookDao;
import com.wellmail.model.NoteBook;
import com.wellmail.model.NoteType;
import com.wellmail.model.Users;

public class NoteBookDaoImpl extends HibernateDaoSupport implements NoteBookDao  {

	@SuppressWarnings("unchecked")
	public List<NoteBook> queryNoteBookByNTIAndUname(NoteType notetype,
			Users users) {
		List<NoteBook> noteBookList = this.getHibernateTemplate().find("from NoteBook where username='"
				+ users.getUsername()+"' and notetypeid="+notetype.getNotetypeid());
		
		if(noteBookList.size() > 0) {
			return noteBookList;
		}
		return null;
	}

	public void addNoteBook(NoteBook notebook) {

		this.getHibernateTemplate().save(notebook);
	}

	@SuppressWarnings("unchecked")
	public NoteBook queryNoteBookByNoteId(NoteBook notebook) {
		List<NoteBook> noteBookList = this.getHibernateTemplate().find("from NoteBook where noteid="
				+ notebook.getNoteid());
		
		if(noteBookList.size() > 0) {
			return noteBookList.get(0);
		}
		return null;
	}

	public void delNoteBook(NoteBook notebook) {

		this.getHibernateTemplate().delete(notebook);
	}

	public void modifyNoteBook(NoteBook notebook) {

		this.getHibernateTemplate().update(notebook);
	}

	@SuppressWarnings("unchecked")
	public List<NoteBook> queryNoteBookByUsers(Users users) {
		List<NoteBook> noteBookList = this.getHibernateTemplate().find("from NoteBook where username='"
				+ users.getUsername()+"'");
		
		if(noteBookList.size() > 0) {
			return noteBookList;
		}
		return null;
	}

}
