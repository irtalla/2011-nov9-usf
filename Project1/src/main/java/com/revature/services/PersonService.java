package com.revature.services;

import java.util.Set;

import com.revature.beans.Genre;
import com.revature.beans.Person;
import com.revature.beans.Role;

public interface PersonService extends GenericService<Person>{
	Person getByUsername(String username);
	//integration methods:
//	public Draft submitDraftForProofreading(Person author, Draft draft);

	Set<Person> getAllPeopleWithRole(Role role);

	Set<Person> getAllEditorsWithRole(Role role, Genre genre);
}
