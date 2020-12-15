package com.trms.data;

import com.trms.beans.Person;

public interface PersonDAO extends GenericDAO<Person> {
	public Person add(Person p);
	public Person getByUsername(String username);

}
