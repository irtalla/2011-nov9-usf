package dev.warrington.data;

import dev.warrington.exceptions.NonUniqueUsernameException;

import dev.warrington.beans.Person;

public interface PersonDAO extends GenericDAO {
	public Person getByUsername(String username);
	public Person add(Person p) throws NonUniqueUsernameException;
}