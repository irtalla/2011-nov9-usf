package dev.warrington.data;

import dev.warrington.beans.Person;
import dev.warrington.data.GenericDAO;
import dev.warrington.exceptions.NonUniqueUsernameException;

public interface PersonDAO extends GenericDAO<Person> {
	
	public Person add(Person p) throws NonUniqueUsernameException;
	
	public Person getByUsername(String username);
	
}
