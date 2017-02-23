package com.wellmail.bean;

import java.io.Serializable;
import java.util.List;

import com.wellmail.model.Contact;
import com.wellmail.model.ContactGroup;

@SuppressWarnings("serial")
public class ContactBean implements Serializable{

	private ContactGroup contactgroup;
	
	private List<Contact> contactList;

	public ContactGroup getContactgroup() {
		return contactgroup;
	}

	public void setContactgroup(ContactGroup contactgroup) {
		this.contactgroup = contactgroup;
	}

	public List<Contact> getContactList() {
		return contactList;
	}

	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}
	
}
