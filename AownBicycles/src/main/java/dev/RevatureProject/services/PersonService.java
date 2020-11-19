package dev.RevatureProject.services;

import dev.RevatureProject.models.Person;

public interface PersonService {


	public Integer addPerson(Person p) ;
	public Person getPersonById(Integer id);
	public Person getPersonByUsername(String username);
	// update
	public void updatePerson(Person p);
	// delete
	public void deletePerson(Person p);
}
