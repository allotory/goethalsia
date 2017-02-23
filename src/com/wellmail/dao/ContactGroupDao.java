package com.wellmail.dao;

import java.util.List;

import com.wellmail.model.ContactGroup;
import com.wellmail.model.Users;

public interface ContactGroupDao {

	public void addContactGroup(ContactGroup contactgroup);
	
	public List<ContactGroup> queryContactByUname(Users users);
	
	public ContactGroup queryContactGroupByGroupId(ContactGroup contactgroup);
	
	public void delContactGroup(ContactGroup contactGroup);
	
	public void modifyContactGroup(ContactGroup contactgroup);
}
