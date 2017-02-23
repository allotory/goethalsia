package com.wellmail.dao;

import com.wellmail.model.Priority;


public interface PriorityDao {

	public void addPriority(Priority priority);
	
	public Priority queryPriorityById(Priority priority);
	
	public Priority queryPriorityByName(Priority priority);
	
}
