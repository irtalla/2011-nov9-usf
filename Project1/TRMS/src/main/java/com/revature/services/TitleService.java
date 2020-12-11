package com.revature.services;

import java.util.Set;

import com.revature.beans.Person;
import com.revature.beans.Title;

public interface TitleService {
	
	// insert method
	public Title addTitle(Title t);
	
	// "read" methods
	public Title getTitleById(Integer id);
	
	public Set<Title> getAllTitles();
	
	public Set<Title> getTitlesByPersonId(Integer person_id);
	
	// "update" methods
	public void updateTitle(Title t);
	
	// "delete" methods
	public void removeTitle(Title t);

}

