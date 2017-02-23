package com.wellmail.dao;

import java.util.List;

import com.wellmail.model.NoteType;
import com.wellmail.model.Users;

public interface NoteTypeDao {

	public void addNoteType(NoteType notetype);
	
	public List<NoteType> queryNoteTypeByUname(Users users);
	
	public NoteType queryNoteTypeById(NoteType notetype);
	
	public void modifyNoteType(NoteType notetype);
	
	public void deleteNoteType(NoteType notetype);
}
