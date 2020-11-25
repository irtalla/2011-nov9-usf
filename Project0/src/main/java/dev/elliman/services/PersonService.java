package dev.elliman.services;

import java.util.Set;

import dev.elliman.beans.Bike;
import dev.elliman.beans.Offer;
import dev.elliman.beans.Person;
import dev.elliman.exceptions.NonUniqueUsernameException;

public interface PersonService {

	//create
	public Integer createUser(Person person) throws NonUniqueUsernameException;
	public void addAdminUser();

	//read
	public Person getPersonById(Integer id);
	public Person getPersonByUsername(String username);
	public Person getAdminUser();
	public Set<Person> getAllUsers();

	//update
	public void updatePerson(Person p);
	
	//delete
	public void deletePerson(Person p);
}
