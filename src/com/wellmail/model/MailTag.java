package com.wellmail.model;

import java.util.HashSet;
import java.util.Set;

public class MailTag {

	//邮件标记标签id
	private	int tagid;
	
	//邮件标记标签名称(1.重要邮件 2.公司邮件 3.业务邮件 4.资讯邮件 5.亲友邮件 6.同学邮件 7.休闲邮件 8.趣闻邮件 9.杂项邮件)（默认-杂项））
	private String tagname;
	
	//email外键
	private Set<Email> emails = new HashSet<Email>();

	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	public int getTagid() {
		return tagid;
	}

	public void setTagid(int tagid) {
		this.tagid = tagid;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
}
