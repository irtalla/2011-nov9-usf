package com.trms.services;

import com.trms.beans.Person;

public interface PersonService {
	public Integer addPerson(Person p);
	public Person getPersonById(Integer id);
	public Person getPersonByUsername(String username);
	public void updatePerson(Person p);
	public void deletePerson(Person p);

}
