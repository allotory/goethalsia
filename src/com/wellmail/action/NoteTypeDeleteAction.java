package com.wellmail.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wellmail.dao.NoteBookDao;
import com.wellmail.dao.NoteTypeDao;
import com.wellmail.dao.UsersDao;
import com.wellmail.model.NoteBook;
import com.wellmail.model.NoteType;
import com.wellmail.model.Users;

public class NoteTypeDeleteAction extends Action {
	
	private Users users;
	private UsersDao usersDao;
	
	private NoteTypeDao noteTypeDao;
	private NoteType notetype;
	
	private NoteBookDao noteBookDao;
	private NoteBook notebook;
	
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		users = (Users)request.getSession().getAttribute("user");
		users = usersDao.queryUserByName(users);
		
		String delnotetype[] = request.getParameterValues("mid");
		
		if(delnotetype.length > 0) {
			for(int i = 0; i<delnotetype.length; i++) {
				notetype.setNotetypeid(Integer.parseInt(delnotetype[i]));
				notetype = noteTypeDao.queryNoteTypeById(notetype);
				
				notebook.setNotetype(notetype);
				List<NoteBook> noteBookList = noteBookDao.queryNoteBookByNTIAndUname(notetype, users);
				if(noteBookList == null) {
					noteTypeDao.deleteNoteType(notetype);
				}else {
					request.setAttribute("flag","deletenotetypeerror");
					return mapping.findForward("failure");
				}
			}
		}else {
			
			return mapping.findForward("failure");
		}
		
		List<NoteType> noteTypeList = noteTypeDao.queryNoteTypeByUname(users);
		request.getSession().setAttribute("noteTypeList", noteTypeList);
		
		return mapping.findForward("success");
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void setNoteTypeDao(NoteTypeDao noteTypeDao) {
		this.noteTypeDao = noteTypeDao;
	}

	public void setNotetype(NoteType notetype) {
		this.notetype = notetype;
	}

	public void setNoteBookDao(NoteBookDao noteBookDao) {
		this.noteBookDao = noteBookDao;
	}

	public void setNotebook(NoteBook notebook) {
		this.notebook = notebook;
	}
}
