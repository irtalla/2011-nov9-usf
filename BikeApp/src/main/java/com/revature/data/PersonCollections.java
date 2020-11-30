package com.revature.data;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.exceptions.NonUniqueUsernameException;

public class PersonCollections implements PersonDAO{
	private Set<Person> people;
	
	public PersonCollections(){
		people = new HashSet<>();
		
		Person p = new Person();
		p.setId(1);
		p.setUsername("jose");
		p.setPassword("password");
		Offer o = new Offer();
		o.setOffer(0.0);
		Role r = new Role();
		r.setName("Employee");
		r.setId(1);
		p.setRole(r);
		people.add(p);
		
		p.setId(2);
		p.setUsername("customer");
		p.setPassword("pass");
		o.setOffer(0.0);
		r.setName("Employee");
		r.setId(2);
		p.setRole(r);
		people.add(p);
	}
	
	public Person getById(Integer id) {
		for (Person p: people) {
			if (p.getId().equals(id))
				return p;
		}
		return null;
	}



	public Set<Person> getAll() {
		return people;
	}



	public void update(Person t) {
		for (Person p: people) {
			if (p.getId() == t.getId()) {
				this.delete(p);
				people.add(t);
				return;
			}
		}
		
	}



	public void delete(Person t) {
		people.remove(t);		
	}



	public Person add(Person t) throws NonUniqueUsernameException {
		for (Person p : people) {
			if (p.getUsername().equals(t.getUsername())) {
				throw new NonUniqueUsernameException();
			}
		}
		t.setId(people.size()+1);
		people.add(t);
		return t;
	}



	public Person getByUsername(String username) {
		for (Person p: people) {
			if (p.getUsername().equals(username))
				return p;
		}
		return null;
	}

	@Override
	public Person setNewOffer(Double offer) {
		Offer noffer = new Offer();
		noffer.setOffer(offer);
		return null;
		
	}

}
