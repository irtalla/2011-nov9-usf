package com.revature.data;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Person;
import com.revature.beans.Role;

public class PersonCollections implements PersonDAO {
	private Set<Person> people;
	
	public PersonCollections() {
		people = new HashSet<>();
		
		Person p = new Person();
		p.setId(1);
		p.setUsername("sierra");
		p.setPassword("pass");
		Role r = new Role();
		r.setId(1);
		r.setName("Employee");
		p.setRole(r);
		people.add(p);
	}

	@Override
	public Person add(Person t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Person getByUsername(String username) {
		return null;
	}

	@Override
	public Set<Person> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Person t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Person t) {
		// TODO Auto-generated method stub

	}

}
