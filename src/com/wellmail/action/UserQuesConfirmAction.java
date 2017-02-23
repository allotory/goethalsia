package com.wellmail.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wellmail.form.UserPwdForm;
import com.wellmail.md5.MD5;
import com.wellmail.model.UserPwd;

public class UserQuesConfirmAction extends Action {
	
	private UserPwd userpwd;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		userpwd = (UserPwd)request.getSession().getAttribute("userpwd");
		
		UserPwdForm upf = (UserPwdForm)form;
		String answer = new String(upf.getAnswer().getBytes("iso8859-1"),"GB18030");
		answer = MD5.toMD5(answer);
		
		if(answer.equals(userpwd.getAnswer())) {
		
			request.setAttribute("user", userpwd.getUser());
			return mapping.findForward("success");
		}
		
		request.setAttribute("flag", "answererror");
		return mapping.findForward("failure");
	}

	public void setUserpwd(UserPwd userpwd) {
		this.userpwd = userpwd;
	}
	
	
}
