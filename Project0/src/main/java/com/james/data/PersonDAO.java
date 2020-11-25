package com.james.data;

import com.james.beans.Person;
import com.james.exceptions.NonUniqueUsernameException;

public interface PersonDAO extends GenericDAO<Person> {
	public Person add(Person p) throws NonUniqueUsernameException;
	public Person getByUsername(String username);
}
