package com.wellmail.model;

import java.util.HashSet;
import java.util.Set;

public class Email {

	//邮件id
	private int emailid;
	
	//当前用户
	private String username;
	
	//发件人
	private String sender;
	
	//收件人
	private String recipients;
	
	//邮件主题
	private String subject;
	
	//邮件内容
	private String content;
	
	//邮件发送时间
	private String senttime;
	
	//邮件类型
	private String mailtype;
	
	//邮件大小
	private double msgsize;
	
	//是否已读（0：已读 ， 1：未读）
	private int unread;
	
	//attachment附件集合
	private Set<Attachment> attachments = new HashSet<Attachment>(); 
	
	//外键folderid
	private Folder folder;
	
	//cc抄送人集合
	private Set<CC> ccs = new HashSet<CC>();
	
	//bcc密送人集合
	private Set<BCC> bccs = new HashSet<BCC>();
	
	//外籍priorityid
	private Priority priority; 
	
	//mailtag集合
	private MailTag mailtag;
	
	//外键user
	private Users user;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Set<BCC> getBccs() {
		return bccs;
	}

	public void setBccs(Set<BCC> bccs) {
		this.bccs = bccs;
	}

	public Set<CC> getCcs() {
		return ccs;
	}

	public void setCcs(Set<CC> ccs) {
		this.ccs = ccs;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public int getEmailid() {
		return emailid;
	}

	public void setEmailid(int emailid) {
		this.emailid = emailid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getMailtype() {
		return mailtype;
	}

	public void setMailtype(String mailtype) {
		this.mailtype = mailtype;
	}

	public double getMsgsize() {
		return msgsize;
	}

	public void setMsgsize(double msgsize) {
		this.msgsize = msgsize;
	}

	public int getUnread() {
		return unread;
	}

	public void setUnread(int unread) {
		this.unread = unread;
	}

	public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

	public MailTag getMailtag() {
		return mailtag;
	}

	public void setMailtag(MailTag mailtag) {
		this.mailtag = mailtag;
	}
	
}
