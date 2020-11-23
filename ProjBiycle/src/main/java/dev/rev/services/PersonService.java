package dev.rev.services;

import dev.rev.customException.NonUniqueUsernameException;
import dev.rev.model.Person;

public interface PersonService {
	
	public Integer addPerson(Person p) throws NonUniqueUsernameException;
	public Person getPersonById(Integer id);
	public Person getPersonByUsername(String username,String password);
	public void updatePerson(Person p);
	public void deletePerson(Person p);
	public String checkusername(String username);

}
