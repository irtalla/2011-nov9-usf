package com.bikeshop.services;

import com.bikeshop.beans.Person;

public interface PersonService {
	// create
	public Person addPerson(Person p) ;
	// read
	public Person getPersonById(Integer id);
	public Person getPersonByUsername(String username);
	// update
	public Person updatePerson(Person p);
	// delete
	boolean delete(Person p);

	public Person registerUser();
	public Person logInUser();
	public Person registerEmployee();
	public void terminate();

}

