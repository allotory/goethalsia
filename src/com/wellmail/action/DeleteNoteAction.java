package com.wellmail.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wellmail.bean.NoteBookBean;
import com.wellmail.dao.NoteBookDao;
import com.wellmail.dao.NoteTypeDao;
import com.wellmail.dao.UsersDao;
import com.wellmail.model.NoteBook;
import com.wellmail.model.NoteType;
import com.wellmail.model.Users;

public class DeleteNoteAction extends Action {
	
	
	private UsersDao usersDao;
	private Users users;
	
	private NoteTypeDao noteTypeDao;
	private NoteType notetype;
	
	private NoteBookDao noteBookDao;
	private NoteBook notebook;
	
	private NoteBookBean notebookbean;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		users = (Users)request.getSession().getAttribute("user");
		users = usersDao.queryUserByName(users);
		
		
		//新建记事本
		String delnote[] = request.getParameterValues("mid");
		
		if(delnote.length > 0) {
			for(int i = 0; i<delnote.length; i++) {
				notebook.setNoteid(Integer.parseInt(delnote[i]));
				notebook = noteBookDao.queryNoteBookByNoteId(notebook);
				
				notetype.setNotetypeid(notebook.getNotetype().getNotetypeid());
				notetype = noteTypeDao.queryNoteTypeById(notetype);
				
				notetype.setContainfilecount(notetype.getContainfilecount() - 1);
				noteTypeDao.modifyNoteType(notetype);
				
				noteBookDao.delNoteBook(notebook);
			}
		}else {
			
			return mapping.findForward("failure");
		}
		
		//查询
		List<NoteType> noteTypeList = noteTypeDao.queryNoteTypeByUname(users);
		List<NoteBook> noteBookList = noteBookDao.queryNoteBookByUsers(users);
		
		List<NoteBookBean> noteBookBeanList = new ArrayList<NoteBookBean>();
		
		if(noteBookList != null) {
			
			for(Iterator<NoteBook> i = noteBookList.iterator();i.hasNext();) {
				notebook = i.next();
				notebookbean = new NoteBookBean();
				notetype = noteTypeDao.queryNoteTypeById(notebook.getNotetype());
				
				notebookbean.setNotetype(notetype);
				notebookbean.setNotebook(notebook);
				noteBookBeanList.add(notebookbean);
				
				notebookbean = null;
			}
		}
		request.getSession().setAttribute("noteBookBeanList", noteBookBeanList);
		request.getSession().setAttribute("noteTypeList", noteTypeList);
		
		return mapping.findForward("success");
	}

	public void setNotebook(NoteBook notebook) {
		this.notebook = notebook;
	}

	public void setNotebookbean(NoteBookBean notebookbean) {
		this.notebookbean = notebookbean;
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

	public void setNoteBookDao(NoteBookDao noteBookDao) {
		this.noteBookDao = noteBookDao;
	}

}
