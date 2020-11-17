package dev.elliman.data;

import java.util.HashSet;
import java.util.Set;

import dev.elliman.beans.Person;
import dev.elliman.beans.Role;

public class PersonCollection implements PersonDAO{
	
	private Set<Person> users;
	
	public PersonCollection() {
		users = new HashSet<Person>();
	}

	public Integer add(Person p) {
		
		p.setID(users.size());
		users.add(p);
		
		return p.getID();
	}

	public Person getByID(Integer id) {
		for(Person p : users) {
			if(p.getID() == id) {
				return p;
			}
		}
		return null;
	}

	public Set<Person> getAll() {
		return users;
	}

	public void update(Person p) {
		Person match = getByID(p.getID());
		
		if(match != null) {
			match.setFirstName(p.getFirstName());
			match.setLastName(p.getLastName());
			match.setRole(p.getRole());
			match.setPassword(p.getPassword());
			match.setUsername(p.getUsername());
		} else {
			users.add(p);
		}
	}

	public void delete(Person p) {
		Person match = getByID(p.getID());
		if(match != null) {
			users.remove(match);
		}
	}
}
