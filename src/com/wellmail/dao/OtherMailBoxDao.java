package com.wellmail.dao;

import java.util.List;

import com.wellmail.model.OtherMailBox;
import com.wellmail.model.Users;

public interface OtherMailBoxDao {

	public void addOtherMailBox(OtherMailBox othermailbox);
	
	public List<OtherMailBox> queryAllOtheraMailBox(Users users);
	
	public OtherMailBox queryOtherMailBoxByUid(OtherMailBox othermailbox);
	
	public void deleteOtherMailBox(OtherMailBox othermailbox);
}
