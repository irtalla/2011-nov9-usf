package dev.elliman.services;

import dev.elliman.beans.Person;

public interface PersonService {
	
	//read
	public Person login(String username, String password);
	
	//write
	public Boolean update(Person person);
}
