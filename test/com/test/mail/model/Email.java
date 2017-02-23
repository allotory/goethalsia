package com.test.mail.model;

public class Email {

	//邮件id
	private int emailid;	
	
	//发件人
	private String sender;		
	
	//收件人
	private String recipients;
	
	//邮件标题
	private String subject;	
	
	//邮件内容
	private String content;
	
	//发送时间
	private String senttime;
	
	//附件
	private String attachement;	
	
	//邮件类型
	private int mailtype;	
	
	//邮件messageid
	private String messageid ;
	
	//邮件大小
	private int msgsize;
	
	//邮件状态(0.已读 1.未读)
	private int unread;
	
	//所属文件夹（1发件箱，2收件箱，3草稿箱，4已发送，5已删除，6垃圾邮件箱，6广告邮件）
	private int folderid;

	public int getEmailid() {
		return emailid;
	}

	public void setEmailid(int emailid) {
		this.emailid = emailid;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
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

	public String getSenttime() {
		return senttime;
	}

	public void setSenttime(String senttime) {
		this.senttime = senttime;
	}

	public String getAttachement() {
		return attachement;
	}

	public void setAttachement(String attachement) {
		this.attachement = attachement;
	}

	public int getMailtype() {
		return mailtype;
	}

	public void setMailtype(int mailtype) {
		this.mailtype = mailtype;
	}

	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	public int getMsgsize() {
		return msgsize;
	}

	public void setMsgsize(int msgsize) {
		this.msgsize = msgsize;
	}

	public int getUnread() {
		return unread;
	}

	public void setUnread(int unread) {
		this.unread = unread;
	}

	public int getFolderid() {
		return folderid;
	}

	public void setFolderid(int folderid) {
		this.folderid = folderid;
	}
	
	
}
