package dev.RevatureProject.Data;

import dev.RevatureProject.models.Person;

public interface PersonDAO extends GenericDAO<Person>{
	
	public Person add(Person p) ;
	public Person getByUsername(String username);

}
