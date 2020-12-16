package com.revature.data;

import java.util.Set;

import com.revature.beans.Storytype;

public interface StorytypeDAO extends GenericDAO<Storytype>{
	public Set<Storytype> getAll();
}
