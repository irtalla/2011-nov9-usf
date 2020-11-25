package com.bikeshop.dao;

import java.util.HashSet;
import java.util.Set;

import com.bikeshop.beans.*;



public class PersonCollection implements PersonDAO {
	private Set<Person> people = new HashSet<>();
	
	public PersonCollection() {
		
		Person p = new Person();
		p.setId(1);
		p.setUsername("AJ");
		p.setPassword("word");
		Role r = new Role();
		r.setName("Employee");
//		p.setRole(r);
		people.add(p);
	}
	@Override
	public Person add(Person t) {
		
		t.setId(people.size()+1);
		people.add(t);
		
		return t;
	}

	@Override
	public Person getByUsername(String username) {
		for (Person p: people) {
			if (p.getUsername().equals(username))
				return p;
		}
		return null;
	}
	@Override
	public Person getByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Person updatePerson(Person p) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
