package com.bikeshop.dao;

import com.bikeshop.beans.Person;

public interface PersonDAO {
	public Person add(Person p);
	public Person getByUsername(String username);
	public Person getByID(Integer id);
	public Person updatePerson(Person p);
	public boolean delete(Integer id);
}
