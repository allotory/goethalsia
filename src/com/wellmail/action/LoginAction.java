package com.wellmail.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.james.security.DigestUtil;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wellmail.bean.ContactBean;
import com.wellmail.dao.ContactDao;
import com.wellmail.dao.ContactGroupDao;
import com.wellmail.dao.EmailDao;
import com.wellmail.dao.EmailSpaceDao;
import com.wellmail.dao.FolderDao;
import com.wellmail.dao.UserScoreDao;
import com.wellmail.dao.UsersDao;
import com.wellmail.form.UsersForm;
import com.wellmail.model.Contact;
import com.wellmail.model.ContactGroup;
import com.wellmail.model.EmailSpace;
import com.wellmail.model.Folder;
import com.wellmail.model.UserScore;
import com.wellmail.model.Users;

@SuppressWarnings("serial")
public class LoginAction extends Action {
	
	private Users users;
	private UsersDao usersDao;
	
	private UserScoreDao userScoreDao;
	private UserScore userscore;
	
	private EmailSpaceDao emailSpaceDao;
	private EmailSpace emailspace;
	
	private FolderDao folderDao;
	private Folder folder;
	
	private EmailDao emailDao;
	
	private ContactGroupDao contactGroupDao;
	private ContactGroup contactgroup;
	
	private ContactDao contactDao;
	
	private ContactBean contactbean;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		UsersForm uf = (UsersForm)form;
		users.setUsername(uf.getUsername()+"@mymail.com");
		users.setPwdHash(DigestUtil.digestString(uf.getPwdHash(),"SHA"));
		
		String ccode = (String) request.getSession().getAttribute("ccode");
		
		if(ccode.equals(uf.getCheckcode())) {
			if(usersDao.queryUserByNamePwd(users)){
				
				if(request.getParameter("rember") != null && request.getParameter("rember").equals("rem")){
				
					users = usersDao.queryUserByName(users);
					userscore = userScoreDao.queryScoreByUser(users);
					emailspace = emailSpaceDao.querySpaceByUser(users);
					
					folder.setFoldername("收件箱");
					folder = folderDao.queryFolderByUserAndFname(users, folder);
					int unreadcount = emailDao.queryUnreadCount(users,folder);
					
					request.getSession().setAttribute("unreadcount", unreadcount);
					request.getSession().setAttribute("emailspace", emailspace);
					request.getSession().setAttribute("userscore", userscore);
					request.getSession().setAttribute("user", users);
					
					
					//查询contact
					List<ContactGroup> contactGroupList = contactGroupDao.queryContactByUname(users);
					
					List<ContactBean> contactBeanList = new ArrayList<ContactBean>();
					
					if(contactGroupList != null) {
						
						for(Iterator<ContactGroup> i = contactGroupList.iterator(); i.hasNext();) {
							contactgroup = i.next();
							
							List<Contact> contactList = contactDao.queryContactByCGId(contactgroup);
							
							contactbean = new ContactBean();
							contactbean.setContactgroup(contactgroup);
							contactbean.setContactList(contactList);
							contactBeanList.add(contactbean);
							
							contactbean = null;
						}
					}
					request.getSession().setAttribute("contactBeanList", contactBeanList);
					
					List<Contact> contactList = contactDao.queryContactByUname(users);
					request.getSession().setAttribute("contactList", contactList);
					
					
					Cookie cookie1 = new Cookie("username", uf.getUsername());//添加用户名Cookie
	                //新建一个Cook,是键值(key-value)关系   
	                int month1 = 60 * 60 * 24 * 30;// 设置一个月(秒*分*时*天)   
	                cookie1.setMaxAge(month1);// 设置Cook的生存期为一个月   
	                cookie1.setPath("/");//设置存诸路经   
	                //将cook添加到response对象中。由response对象返回给户端   

	                response.addCookie(cookie1);
	                
	                
	                Cookie cookie2 = new Cookie("pwdHash", uf.getPwdHash());        //添加密码Cookie
	                //新建一个Cook,是键值(key-value)关系   
	                int month2 = 60 * 60 * 24 * 30;// 设置一个月(秒*分*时*天)   
	                cookie2.setMaxAge(month2);// 设置Cook的生存期为一个月   
	                cookie2.setPath("/");//设置存诸路经   
	                //将cook添加到response对象中。由response对象返回给户端   

	                response.addCookie(cookie2);
					
					
					return mapping.findForward("success");
				}else {
					users = usersDao.queryUserByName(users);
					userscore = userScoreDao.queryScoreByUser(users);
					emailspace = emailSpaceDao.querySpaceByUser(users);
					
					folder.setFoldername("收件箱");
					folder = folderDao.queryFolderByUserAndFname(users, folder);
					int unreadcount = emailDao.queryUnreadCount(users,folder);
					
					request.getSession().setAttribute("unreadcount", unreadcount);
					request.getSession().setAttribute("emailspace", emailspace);
					request.getSession().setAttribute("userscore", userscore);
					request.getSession().setAttribute("user", users);
					
					
					//查询contact
					List<ContactGroup> contactGroupList = contactGroupDao.queryContactByUname(users);
					
					List<ContactBean> contactBeanList = new ArrayList<ContactBean>();
					
					if(contactGroupList != null) {
						
						for(Iterator<ContactGroup> i = contactGroupList.iterator(); i.hasNext();) {
							contactgroup = i.next();
							
							List<Contact> contactList = contactDao.queryContactByCGId(contactgroup);
							
							contactbean = new ContactBean();
							contactbean.setContactgroup(contactgroup);
							contactbean.setContactList(contactList);
							contactBeanList.add(contactbean);
							
							contactbean = null;
						}
					}
					request.getSession().setAttribute("contactBeanList", contactBeanList);
					
					List<Contact> contactList = contactDao.queryContactByUname(users);
					request.getSession().setAttribute("contactList", contactList);
					
					return mapping.findForward("success");
				}
			}else {
				
				//用户名或密码错误
				request.setAttribute("flag", "nameorpasserror");
				return mapping.findForward("failure");
			}
		}
		
		//登录验证码错误
		request.setAttribute("flag", "checkcodeerror");
		return mapping.findForward("failure");
	}

	public void setContactGroupDao(ContactGroupDao contactGroupDao) {
		this.contactGroupDao = contactGroupDao;
	}

	public void setContactgroup(ContactGroup contactgroup) {
		this.contactgroup = contactgroup;
	}

	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}

	public void setContactbean(ContactBean contactbean) {
		this.contactbean = contactbean;
	}

	public void setFolderDao(FolderDao folderDao) {
		this.folderDao = folderDao;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public void setEmailDao(EmailDao emailDao) {
		this.emailDao = emailDao;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void setUserScoreDao(UserScoreDao userScoreDao) {
		this.userScoreDao = userScoreDao;
	}

	public void setUserscore(UserScore userscore) {
		this.userscore = userscore;
	}

	public void setEmailSpaceDao(EmailSpaceDao emailSpaceDao) {
		this.emailSpaceDao = emailSpaceDao;
	}

	public void setEmailspace(EmailSpace emailspace) {
		this.emailspace = emailspace;
	}
}
