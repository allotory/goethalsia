package com.wellmail.dao;

import java.util.List;

import com.wellmail.model.Contact;
import com.wellmail.model.ContactGroup;
import com.wellmail.model.Users;

public interface ContactDao {

	public void addContact(Contact contact);
	
	public List<Contact> queryContactByCGId(ContactGroup contactgroup);
	
	public List<Contact> queryContactByUname(Users users);
	
	public Contact queryContactByContactId(Contact contact);
	
	public void deleteContact(Contact contact);
	
	public void modifyContact(Contact contact);
	
	public Contact queryContactByEmail(Contact contact);
}
