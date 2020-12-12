package dev.warrington.services;

import dev.warrington.exceptions.NonUniqueUsernameException;

import dev.warrington.beans.Person;

public interface PersonService {
	public Person getPersonByUsername(String username);
	public Integer addPerson(Person p) throws NonUniqueUsernameException;
}