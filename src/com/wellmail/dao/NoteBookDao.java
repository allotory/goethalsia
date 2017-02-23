package com.wellmail.dao;

import java.util.List;

import com.wellmail.model.NoteBook;
import com.wellmail.model.NoteType;
import com.wellmail.model.Users;

public interface NoteBookDao {
	
	public void addNoteBook(NoteBook notebook);

	public List<NoteBook> queryNoteBookByNTIAndUname(NoteType notetype, Users users);
	
	public NoteBook queryNoteBookByNoteId(NoteBook notebook);
	
	public List<NoteBook> queryNoteBookByUsers(Users users);
	
	public void delNoteBook(NoteBook notebook);
	
	public void modifyNoteBook(NoteBook notebook);
}
