package com.wellmail.init;

import com.wellmail.model.ContactGroup;
import com.wellmail.model.Users;

public class ContactGroupInit {

	public ContactGroup initcontactGroup(ContactGroup contactgroup,String groupname,Users user){
		
		contactgroup.setContainusercount(0);
		contactgroup.setGroupname(groupname);
		contactgroup.setUser(user);
		
		return contactgroup;
	}
}
