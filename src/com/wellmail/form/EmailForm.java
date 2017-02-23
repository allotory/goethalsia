package com.wellmail.form;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class EmailForm extends ActionForm {
	
	private int emailid;

	//当前用户
	private String username;
	
	//发件人----不用取了，在action中直接request.getSession（）.getAttribute（"user"）；
	//private String sender;
	
	//收件人
	private String recipients;
	
	//邮件主题
	private String subject;
	
	//邮件内容
	private String content;
	
	//抄送人地址
	private String cc;
	
	//密送人地址
	private String bcc;
	
	//群发单显
	private String qf;
	
	//修改标记为
	private String changeflag;
	
	//移动到
	private String moveto;
	
	//查看
	private String queryfrom;
	
	//附件地址也没有取，直接在action中取
	
	private int pageNo;
	
	private String uname;
	
	private int totalPages;
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public String getChangeflag() {
		return changeflag;
	}

	public void setChangeflag(String changeflag) {
		this.changeflag = changeflag;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRecipients() {
		return recipients;
	}

	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getQf() {
		return qf;
	}

	public void setQf(String qf) {
		this.qf = qf;
	}

	public int getEmailid() {
		return emailid;
	}

	public void setEmailid(int emailid) {
		this.emailid = emailid;
	}

	public String getMoveto() {
		return moveto;
	}

	public void setMoveto(String moveto) {
		this.moveto = moveto;
	}

	public String getQueryfrom() {
		return queryfrom;
	}

	public void setQueryfrom(String queryfrom) {
		this.queryfrom = queryfrom;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	
}
