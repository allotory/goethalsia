package com.wellmail.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.james.security.DigestUtil;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wellmail.dao.ContactGroupDao;
import com.wellmail.dao.EmailSpaceDao;
import com.wellmail.dao.FileTypeDao;
import com.wellmail.dao.FolderDao;
import com.wellmail.dao.NoteTypeDao;
import com.wellmail.dao.UserPwdDao;
import com.wellmail.dao.UserScoreDao;
import com.wellmail.dao.UsersDao;
import com.wellmail.dao.UsersInfoDao;
import com.wellmail.dao.WebDiskDao;
import com.wellmail.form.UsersForm;
import com.wellmail.init.ContactGroupInit;
import com.wellmail.init.FileTypeInit;
import com.wellmail.init.FolderInit;
import com.wellmail.init.NoteTypeInit;
import com.wellmail.md5.MD5;
import com.wellmail.model.ContactGroup;
import com.wellmail.model.EmailSpace;
import com.wellmail.model.FileType;
import com.wellmail.model.Folder;
import com.wellmail.model.NoteType;
import com.wellmail.model.UserPwd;
import com.wellmail.model.UserScore;
import com.wellmail.model.Users;
import com.wellmail.model.UsersInfo;
import com.wellmail.model.WebDisk;

public class RegistingAction extends Action {
	
	//用户
	private UsersDao usersDao;
	private Users users;
	
	//联系人
	private ContactGroupDao contactGroupDao;
	private ContactGroupInit contactGroupInit;
	private ContactGroup contactgroup;
	
	//邮件空间
	private EmailSpaceDao emailSpaceDao;
	private EmailSpace emailspace;
	
	//网盘文件
	private FileTypeDao fileTypeDao;
	private FileTypeInit fileTypeInit;
	private FileType filetype;
	
	//用户密码提示
	private UserPwdDao userPwdDao;
	private UserPwd userpwd;
	
	//用户积分
	private UserScoreDao userScoreDao;
	private UserScore userscore;
	
	//用户个人信息
	private UsersInfoDao usersInfoDao;
	private UsersInfo usersinfo;
	
	//网盘
	private WebDiskDao webDiskDao;
	private WebDisk webdisk;
	
	//文件夹
	private FolderDao folderDao;
	private FolderInit folderInit;
	private Folder folder;
	
	//记事本
	private NoteTypeDao noteTypeDao;
	private NoteType notetype;
	private NoteTypeInit noteTypeInit;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		UsersForm uf = (UsersForm)form;
		
		String ccode = (String) request.getSession().getAttribute("ccode");
		if(ccode.equals(uf.getCheckcode())) {
			
			if(uf.getPwdHash().equals(uf.getPwdHashConfirm())) {
				
				//保存用户表
				users.setUsername(uf.getUsername()+"@mymail.com");
				users.setPwdHash(DigestUtil.digestString(uf.getPwdHash(),"SHA"));
				users.setPwdAlgorithm("SHA");
				users.setUseForwarding(0);
				users.setForwardDestination(null);
				users.setUseAlias(0);
				users.setAlias(null);
				usersDao.addUser(users);
				
				//初始化文件夹
				folder = folderInit.initFolder(folder, "收件箱", users);
				folderDao.addFolder(folder);
				folder = folderInit.initFolder(folder, "草稿箱", users);
				folderDao.addFolder(folder);
				folder = folderInit.initFolder(folder, "已发送", users);
				folderDao.addFolder(folder);
				folder = folderInit.initFolder(folder, "已删除", users);
				folderDao.addFolder(folder);
				folder = folderInit.initFolder(folder, "垃圾邮件", users);
				folderDao.addFolder(folder);
				folder = folderInit.initFolder(folder, "广告邮件", users);
				folderDao.addFolder(folder);
				
				//初始化邮箱空间
				emailspace.setEspace(2048);
				emailspace.setSpaceleft(2048);
				emailspace.setUser(users);
				emailSpaceDao.addEmailSpace(emailspace);
				
				//初始化用户密码提示问题
				String question = new String(uf.getQuestion().getBytes("iso8859-1"),"GB18030");
				String answer = new String(uf.getAnswer().getBytes("iso8859-1"),"GB18030");
				//md5加密
				answer = MD5.toMD5(answer);
				userpwd.setQuestion(question);
				userpwd.setAnswer(answer);
				userpwd.setUser(users);
				userPwdDao.addUserPwd(userpwd);
				
				//初始化用户积分
				userscore.setLevel(1);
				userscore.setScore(20);
				userscore.setUser(users);
				userScoreDao.addUserScore(userscore);
				
				//初始化用户信息
				String sex = new String(uf.getSex().getBytes("ISO8859-1"),"GB18030");
				usersinfo.setSex(sex);
				usersinfo.setBirthday(uf.getYear() +"."+ uf.getMonth() +"."+uf.getDay());
				if(uf.getTel() != null || uf.getTel() != ""){
					usersinfo.setTel(uf.getTel());
				}else {
					usersinfo.setTel("");
				}
				usersinfo.setUser(users);
				usersInfoDao.addUsersInfo(usersinfo);
				
				//初始化联系人组
				contactgroup = contactGroupInit.initcontactGroup(contactgroup, "朋友", users);
				contactGroupDao.addContactGroup(contactgroup);
				contactgroup = contactGroupInit.initcontactGroup(contactgroup, "亲人", users);
				contactGroupDao.addContactGroup(contactgroup);
				contactgroup = contactGroupInit.initcontactGroup(contactgroup, "同事", users);
				contactGroupDao.addContactGroup(contactgroup);
				contactgroup = contactGroupInit.initcontactGroup(contactgroup, "老师", users);
				contactGroupDao.addContactGroup(contactgroup);
				contactgroup = contactGroupInit.initcontactGroup(contactgroup, "同学", users);
				contactGroupDao.addContactGroup(contactgroup);
				
				//初始化记事本类型
				notetype = noteTypeInit.initNoteType(notetype, "普通记事", users);
				noteTypeDao.addNoteType(notetype);
				notetype = noteTypeInit.initNoteType(notetype, "待办记事", users);
				noteTypeDao.addNoteType(notetype);
				notetype = noteTypeInit.initNoteType(notetype, "其他记事", users);
				noteTypeDao.addNoteType(notetype);
				
				//初始化网盘空间
				webdisk.setWebspace(1024);
				webdisk.setWsleft(1024);
				webdisk.setFilecount(0);
				webdisk.setUser(users);
				webDiskDao.addWebDisk(webdisk);
				
				//初始化网盘文件分类
				filetype = fileTypeInit.initFileType(filetype, "我的文档", users);
				fileTypeDao.addFileType(filetype);
				filetype = fileTypeInit.initFileType(filetype, "我的图片", users);
				fileTypeDao.addFileType(filetype);
				filetype = fileTypeInit.initFileType(filetype, "我的音乐", users);
				fileTypeDao.addFileType(filetype);
				filetype = fileTypeInit.initFileType(filetype, "我的多媒体", users);
				fileTypeDao.addFileType(filetype);
				filetype = fileTypeInit.initFileType(filetype, "我的软件", users);
				fileTypeDao.addFileType(filetype);
				
				//成功跳转
				request.setAttribute("flag", "addusersuccess");
				return mapping.findForward("success");
			}else {
				
				//两次密码不一致
				request.setAttribute("flag", "passwordnotequal");
				return mapping.findForward("failure");
			}
		}
		
		//验证码错误
		request.setAttribute("flag", "checkcodeerror");
		return mapping.findForward("failure");
	}

	public void setFileTypeInit(FileTypeInit fileTypeInit) {
		this.fileTypeInit = fileTypeInit;
	}

	public void setFiletype(FileType filetype) {
		this.filetype = filetype;
	}

	public void setWebdisk(WebDisk webdisk) {
		this.webdisk = webdisk;
	}

	public void setNoteTypeInit(NoteTypeInit noteTypeInit) {
		this.noteTypeInit = noteTypeInit;
	}

	public void setFolderDao(FolderDao folderDao) {
		this.folderDao = folderDao;
	}

	public void setNoteTypeDao(NoteTypeDao noteTypeDao) {
		this.noteTypeDao = noteTypeDao;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void setContactGroupDao(ContactGroupDao contactGroupDao) {
		this.contactGroupDao = contactGroupDao;
	}

	public void setEmailSpaceDao(EmailSpaceDao emailSpaceDao) {
		this.emailSpaceDao = emailSpaceDao;
	}

	public void setFileTypeDao(FileTypeDao fileTypeDao) {
		this.fileTypeDao = fileTypeDao;
	}

	public void setUserPwdDao(UserPwdDao userPwdDao) {
		this.userPwdDao = userPwdDao;
	}

	public void setUserScoreDao(UserScoreDao userScoreDao) {
		this.userScoreDao = userScoreDao;
	}

	public void setUsersInfoDao(UsersInfoDao usersInfoDao) {
		this.usersInfoDao = usersInfoDao;
	}

	public void setWebDiskDao(WebDiskDao webDiskDao) {
		this.webDiskDao = webDiskDao;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public void setFolderInit(FolderInit folderInit) {
		this.folderInit = folderInit;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public void setEmailspace(EmailSpace emailspace) {
		this.emailspace = emailspace;
	}

	public void setUserpwd(UserPwd userpwd) {
		this.userpwd = userpwd;
	}

	public void setUserscore(UserScore userscore) {
		this.userscore = userscore;
	}

	public void setUsersinfo(UsersInfo usersinfo) {
		this.usersinfo = usersinfo;
	}

	public void setContactGroupInit(ContactGroupInit contactGroupInit) {
		this.contactGroupInit = contactGroupInit;
	}

	public void setContactgroup(ContactGroup contactgroup) {
		this.contactgroup = contactgroup;
	}

	public void setNotetype(NoteType notetype) {
		this.notetype = notetype;
	}
}
