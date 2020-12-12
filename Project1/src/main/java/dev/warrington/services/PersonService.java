package dev.warrington.services;

import dev.warrington.beans.Person;

public interface PersonService {
	
	public Person getPersonByUsername(String username);
	
}