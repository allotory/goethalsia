package com.wellmail.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wellmail.dao.PriorityDao;
import com.wellmail.model.Priority;

public class PriorityDaoImpl extends HibernateDaoSupport implements PriorityDao {

	public void addPriority(Priority priority) {

		this.getHibernateTemplate().save(priority);
	}

	@SuppressWarnings("unchecked")
	public Priority queryPriorityById(Priority priority) {

		List<Priority> priorityList = this.getHibernateTemplate().find("from Priority where priorityid="+ priority.getPriorityid());
		
		Priority prioritys = new Priority();
		if(priorityList.size() == 1) {
			prioritys = priorityList.get(0);
			return prioritys;
			
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Priority queryPriorityByName(Priority priority) {
		List<Priority> priorityList = this.getHibernateTemplate().find("from Priority where priorityname='"+ priority.getPriorityname() +"'");
		
		Priority prioritys = new Priority();
		if(priorityList.size() == 1) {
			prioritys = priorityList.get(0);
			return prioritys;
			
		}
		return null;
	}
}
