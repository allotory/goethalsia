package com.wellmail.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ClearCookieAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		Cookie cookie1 = new Cookie("username", "");//添加用户名Cookie
        //新建一个Cook,是键值(key-value)关系   
        int month1 = 60 * 60 * 24 * 30;// 设置一个月(秒*分*时*天)   
        cookie1.setMaxAge(month1);// 设置Cook的生存期为一个月   
        cookie1.setPath("/");//设置存诸路经   
        //将cook添加到response对象中。由response对象返回给户端   

        response.addCookie(cookie1);
        
        Cookie cookie2 = new Cookie("pwdHash", "");        //添加密码Cookie
        //新建一个Cook,是键值(key-value)关系   
        int month2 = 60 * 60 * 24 * 30;// 设置一个月(秒*分*时*天)   
        cookie2.setMaxAge(month2);// 设置Cook的生存期为一个月   
        cookie2.setPath("/");//设置存诸路经   
        //将cook添加到response对象中。由response对象返回给户端   

        response.addCookie(cookie2);
		
		return mapping.findForward("success");
	}
}
