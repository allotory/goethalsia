package com.wellmail.dao;

import java.util.List;

import com.wellmail.model.OtherMailBoxType;

public interface OtherMailBoxTypeDao {

	public List<OtherMailBoxType> queryAllOtherMailBoxType();
	
	public OtherMailBoxType queryOtherMailBoxTypeByTid(OtherMailBoxType othermailboxtype);
}
