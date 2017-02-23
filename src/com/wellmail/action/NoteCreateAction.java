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
import com.wellmail.form.NoteForm;
import com.wellmail.model.NoteBook;
import com.wellmail.model.NoteType;
import com.wellmail.model.Users;

public class NoteCreateAction extends Action {
	
	
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
		NoteForm nf = (NoteForm)form;
		
		String notetitle = new String(nf.getNotetitle().getBytes("ISO8859-1"),"GB18030");
		String notecontent =  new String(nf.getNotecontent().getBytes("ISO8859-1"),"GB18030");
		int notetypeid = nf.getNotetypeid();
		
		notetype.setNotetypeid(notetypeid);
		notetype = noteTypeDao.queryNoteTypeById(notetype);
		
		notetype.setContainfilecount(notetype.getContainfilecount() + 1);
		noteTypeDao.modifyNoteType(notetype);
		
		notebook.setNotetitle(notetitle);
		notebook.setNotecontent(notecontent);
		notebook.setNotetype(notetype);
		notebook.setUser(users);
		
		noteBookDao.addNoteBook(notebook);
		
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
		
		
		//分页
		int pageSize = 7;
		
		
		int pageNo = nf.getPageNo();
		
		if(pageNo <= 0) {
			pageNo = 1;
		}
		
		int totalRecords = noteBookBeanList.size();
		//System.out.println(totalRecords+"____totalRecords");
		
		int totalPages = totalRecords%pageSize == 0? totalRecords/pageSize : totalRecords/pageSize +1;
		
		if(pageNo > totalPages) {
			pageNo = totalPages;
		}
		
		int startPos = (pageNo - 1) * pageSize;
		//System.out.println(startPos+"____startPos");
		
		List<NoteBookBean> noteBookBeanListTemp = new ArrayList<NoteBookBean>();
		
		//System.out.println(pageNo+"*****");
		for(int i = 0; i<pageNo-1; i++) {
			pageSize += 7;
		}
		//System.out.println(pageSize+"****");
		
		if(startPos >= totalRecords - totalRecords%pageSize) {
			pageSize = totalRecords%pageSize;
		}
		if(noteBookBeanList.size() > 0) {
			for(int i = startPos; i < pageSize; i++) {
				noteBookBeanListTemp.add(noteBookBeanList.get(i));
				//System.out.println(articles.get(i)+"--------");
			}
		}

		request.getSession().setAttribute("noteBookPageNo", pageNo);
		request.getSession().setAttribute("noteBookTotalPages", totalPages);
		
		request.getSession().setAttribute("noteBookBeanList", noteBookBeanListTemp);
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
