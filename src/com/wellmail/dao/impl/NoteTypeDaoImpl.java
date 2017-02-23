package com.wellmail.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wellmail.dao.NoteTypeDao;
import com.wellmail.model.NoteType;
import com.wellmail.model.Users;

public class NoteTypeDaoImpl extends HibernateDaoSupport implements NoteTypeDao {

	public void addNoteType(NoteType notetype) {

		this.getHibernateTemplate().save(notetype);
	}

	@SuppressWarnings("unchecked")
	public List<NoteType> queryNoteTypeByUname(Users users) {
		List<NoteType> noteTypeList = this.getHibernateTemplate().find("from NoteType where username='"
				+ users.getUsername()+"'");
		
		if(noteTypeList.size() > 0) {
			return noteTypeList;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public NoteType queryNoteTypeById(NoteType notetype) {
		List<NoteType> noteTypeList = this.getHibernateTemplate().find("from NoteType where notetypeid="
				+ notetype.getNotetypeid());
		
		if(noteTypeList.size() > 0) {
			return noteTypeList.get(0);
		}
		return null;
	}

	public void modifyNoteType(NoteType notetype) {

		this.getHibernateTemplate().update(notetype);
	}

	public void deleteNoteType(NoteType notetype) {

		this.getHibernateTemplate().delete(notetype);
	}

}
