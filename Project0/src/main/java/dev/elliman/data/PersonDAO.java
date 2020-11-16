package dev.elliman.data;

import java.util.Set;

import dev.elliman.beans.Person;

public interface PersonDAO {

	public Integer add(Person p);
	
	public Person getByID(Integer id);
	
	public Set<Person> getAll();
	
	public void update(Person p);
	
	public void delete(Person p);
	
	public void addAdminUser();
}
