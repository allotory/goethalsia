package com.wellmail.bean;

import javax.mail.PasswordAuthentication;
/**
 * 校验发信人权限的方法
 * @author Administrator
 *
 */
public class MyAutherticator extends javax.mail.Authenticator {
	private String strUser;
	private String strPwd;

	public MyAutherticator(String user, String password) {
		this.strUser = user;
		this.strPwd = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(strUser, strPwd);
	}
}


