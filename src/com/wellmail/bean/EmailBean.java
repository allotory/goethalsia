package com.wellmail.bean;

import java.util.List;

import com.wellmail.model.Attachment;
import com.wellmail.model.BCC;
import com.wellmail.model.CC;
import com.wellmail.model.Email;
import com.wellmail.model.MailTag;
import com.wellmail.model.Priority;

public class EmailBean {

	private Email email;
	
	private Priority priority;
	
	private MailTag mailtag;
	
	private List<Attachment> attachmentList;
	
	private boolean containAttachment;
	
	private List<CC> ccList;
	
	private List<BCC> bccList;

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public MailTag getMailtag() {
		return mailtag;
	}

	public void setMailtag(MailTag mailtag) {
		this.mailtag = mailtag;
	}

	public List<CC> getCcList() {
		return ccList;
	}

	public void setCcList(List<CC> ccList) {
		this.ccList = ccList;
	}

	public List<BCC> getBccList() {
		return bccList;
	}

	public void setBccList(List<BCC> bccList) {
		this.bccList = bccList;
	}

	public boolean isContainAttachment() {
		return containAttachment;
	}

	public void setContainAttachment(boolean containAttachment) {
		this.containAttachment = containAttachment;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	
}
