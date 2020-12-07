package com.revature.data;

import com.cross.beans.Person;

public interface PersonDAO extends GenericDAO<Person> {
	public Person getByUsername(String username);
}
