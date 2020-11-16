package dev.elliman.data;

import java.util.HashSet;
import java.util.Set;

import dev.elliman.beans.Person;

public class PersonCollection implements PersonDAO{
	
	private Set<Person> users;
	
	public PersonCollection() {
		users = new HashSet<Person>();
		
		Person admin = new Person("", "", "admin", "password", "Manager");
		admin.setID(0);
		
		users.add(admin);
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
		}
	}

	public void delete(Person p) {
		Person match = getByID(p.getID());
		if(match != null) {
			users.remove(match);
		}
	}

	public void addAdminUser() {
		//check for an admin before creating a new one
		Person admin = getByID(0);
		if(admin == null) {
			//no admin found, add a new one
			admin = new Person("", "", "admin", "password", "Manager");
			admin.setID(0);
			users.add(admin);
		}
	}
	
	
}
