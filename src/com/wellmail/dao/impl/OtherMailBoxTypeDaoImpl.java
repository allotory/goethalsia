package com.wellmail.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wellmail.dao.OtherMailBoxTypeDao;
import com.wellmail.model.OtherMailBoxType;

public class OtherMailBoxTypeDaoImpl extends HibernateDaoSupport implements
		OtherMailBoxTypeDao {

	@SuppressWarnings("unchecked")
	public List<OtherMailBoxType> queryAllOtherMailBoxType() {
		
		List<OtherMailBoxType> otherMailBoxTypeList = this.getHibernateTemplate().find("from OtherMailBoxType");
		
		if(otherMailBoxTypeList.size() > 0) {
			return otherMailBoxTypeList;
			
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public OtherMailBoxType queryOtherMailBoxTypeByTid(
			OtherMailBoxType othermailboxtype) {
		List<OtherMailBoxType> otherMailBoxTypeList = this.getHibernateTemplate().find("from OtherMailBoxType where tid="+ othermailboxtype.getTid());
		
		if(otherMailBoxTypeList.size() == 1) {
			return otherMailBoxTypeList.get(0);
			
		}
		return null;
	}

}
