package com.wellmail.init;

import com.wellmail.model.NoteType;
import com.wellmail.model.Users;

public class NoteTypeInit {

	public NoteType initNoteType(NoteType notetype, String notetypename, Users user) {
		
		notetype.setNotetypename(notetypename);
		notetype.setContainfilecount(0);
		notetype.setUser(user);
		
		return notetype;
	}
}
