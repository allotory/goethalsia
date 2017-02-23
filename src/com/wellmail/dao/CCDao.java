package com.wellmail.dao;

import java.util.List;

import com.wellmail.model.CC;
import com.wellmail.model.Email;

public interface CCDao {

	public void addCC(CC cc);
	
	public List<CC> queryCCByEmailId(Email email);
	
	public void delCC(CC ccs);
}
