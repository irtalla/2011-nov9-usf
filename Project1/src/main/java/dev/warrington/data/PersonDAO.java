package dev.warrington.data;

import dev.warrington.beans.Person;

public interface PersonDAO extends GenericDAO {
	
	public Person getByUsername(String username);

}