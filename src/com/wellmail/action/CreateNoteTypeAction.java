package com.wellmail.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wellmail.dao.NoteTypeDao;
import com.wellmail.dao.UsersDao;
import com.wellmail.form.NoteForm;
import com.wellmail.model.NoteType;
import com.wellmail.model.Users;

public class CreateNoteTypeAction extends Action {
	
	private UsersDao usersDao;
	private Users users;
	
	private NoteTypeDao noteTypeDao;
	private NoteType notetype;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		users = (Users) request.getSession().getAttribute("user");
		users = usersDao.queryUserByName(users);
		
		NoteForm nf = (NoteForm) form;
		String notetypename = new String(nf.getNotetypename().getBytes("ISO8859-1"),"Gb18030");
		
		int noteid = nf.getNoteid();
		
		if(noteid == 0) {
			notetype.setNotetypename(notetypename);
			notetype.setContainfilecount(0);
			notetype.setUser(users);
			
			noteTypeDao.addNoteType(notetype);
			
			List<NoteType> noteTypeList = noteTypeDao.queryNoteTypeByUname(users);
			request.getSession().setAttribute("noteTypeList", noteTypeList);
			
			return mapping.findForward("success");
		}else {
			notetype.setNotetypename(notetypename);
			notetype.setContainfilecount(0);
			notetype.setUser(users);
			
			noteTypeDao.addNoteType(notetype);
			
			List<NoteType> noteTypeList = noteTypeDao.queryNoteTypeByUname(users);
			request.getSession().setAttribute("noteTypeList", noteTypeList);
			
			request.setAttribute("noteid", noteid);
			return mapping.findForward("gotonotemodify");
		}
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public void setNoteTypeDao(NoteTypeDao noteTypeDao) {
		this.noteTypeDao = noteTypeDao;
	}

	public void setNotetype(NoteType notetype) {
		this.notetype = notetype;
	}
}
