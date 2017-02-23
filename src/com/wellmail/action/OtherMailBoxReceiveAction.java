package com.wellmail.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wellmail.dao.OtherMailBoxDao;
import com.wellmail.dao.OtherMailBoxTypeDao;
import com.wellmail.dao.UsersDao;
import com.wellmail.form.OtherMailBoxForm;
import com.wellmail.inbox.MailReceiver;
import com.wellmail.inbox.MailReceiverInfo;
import com.wellmail.model.OtherMailBox;
import com.wellmail.model.OtherMailBoxType;
import com.wellmail.model.Users;

public class OtherMailBoxReceiveAction extends Action{
	
	private UsersDao usersDao;
	private Users users;
	
	private OtherMailBoxTypeDao otherMailBoxTypeDao;
	private OtherMailBoxType othermailboxtype;
	
	private OtherMailBoxDao otherMailBoxDao;
	private OtherMailBox othermailbox;
	
	//private OtherMailBoxBean othermailboxbean;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		users = (Users) request.getSession().getAttribute("user");
		users = usersDao.queryUserByName(users);
		
		OtherMailBoxForm ombf = (OtherMailBoxForm)form;
		int  uid = ombf.getUid();
		
		othermailbox.setUid(uid);
		othermailbox = otherMailBoxDao.queryOtherMailBoxByUid(othermailbox);
		
		othermailboxtype.setTid(othermailbox.getOthermailboxtype().getTid());
		othermailboxtype = otherMailBoxTypeDao.queryOtherMailBoxTypeByTid(othermailboxtype);
		
		String uname = othermailbox.getUname();
		String upass = othermailbox.getUpass();
		
		//String smtp = othermailboxtype.getTsmtp();
		String pop3 = othermailboxtype.getTpop3();
		
		//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		MailReceiverInfo receiverInfo = new MailReceiverInfo();
		receiverInfo.setMailServerHost(pop3);
		receiverInfo.setMailServerPort("110");
		receiverInfo.setValidate(true);
		receiverInfo.setUserName(uname);
		receiverInfo.setPassword(upass);
		receiverInfo.setAttachmentDir("../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/mail/");
		receiverInfo.setEmailDir("../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/mail/");

		MailReceiver receiver = new MailReceiver(receiverInfo);
		receiver.receiveAllMail();
		
		
		//System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
		
		/*for(Iterator<OtherMailBean> i = receiver.getOtherMailList().iterator();i.hasNext();) {
			OtherMailBean omb = i.next();
			System.out.println("----------------------------");
			System.out.println("from:"+omb.getFrom());
			System.out.println("subject:"+omb.getSubject());
			System.out.println("path:"+omb.getPath());
			System.out.println("Sentdate:"+omb.getSentdate());
			System.out.println("isNEw:"+omb.isNew());
		}*/
		
		request.getSession().setAttribute("othermaillist",receiver.getOtherMailList());
		request.getSession().setAttribute("othermailwriteuname",uname);
		request.getSession().setAttribute("othermailwritetype",othermailboxtype);
		return mapping.findForward("success");
	}

	public void setOthermailboxtype(OtherMailBoxType othermailboxtype) {
		this.othermailboxtype = othermailboxtype;
	}

	public void setOtherMailBoxDao(OtherMailBoxDao otherMailBoxDao) {
		this.otherMailBoxDao = otherMailBoxDao;
	}

	public void setOthermailbox(OtherMailBox othermailbox) {
		this.othermailbox = othermailbox;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void setUsers(Users users) {
		this.users = users;
	}


	public void setOtherMailBoxTypeDao(OtherMailBoxTypeDao otherMailBoxTypeDao) {
		this.otherMailBoxTypeDao = otherMailBoxTypeDao;
	}
}
