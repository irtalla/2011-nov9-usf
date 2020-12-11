package com.revature.data;

import java.util.Set;

import com.revature.beans.Title;

public interface TitleDAO extends GenericDAO<Title> {
	
	public Title add(Title t);
	public Title getById(Integer id);
	public Set<Title> getAll();
	public void update(Title t);
	public void delete(Title t);
	public Set<Title> getTitlesByPersonId(Integer person_id);
	
}
