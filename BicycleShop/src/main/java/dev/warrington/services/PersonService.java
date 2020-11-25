package dev.warrington.services;

import dev.warrington.beans.Person;
import dev.warrington.exceptions.NonUniqueUsernameException;

public interface PersonService {

	public Integer addPerson(Person p) throws NonUniqueUsernameException;
	
	public Person getPersonById(Integer id);
	
	public Person getPersonByUsername(String username);
	
	public void updatePerson(Person p);
	
	public void deletePerson(Person p);
	
}