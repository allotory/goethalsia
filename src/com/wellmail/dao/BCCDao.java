package com.wellmail.dao;

import java.util.List;

import com.wellmail.model.BCC;
import com.wellmail.model.Email;

public interface BCCDao {

	public void addBCC(BCC bcc);
	
	public List<BCC> queryBCCByEmailId(Email email);
	
	public void delBCC(BCC bccs);
}
