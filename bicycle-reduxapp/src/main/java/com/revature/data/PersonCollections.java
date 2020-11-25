package com.revature.data;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Person;
import com.revature.exception.NonUniqueUsernameException;
import com.revature.services.PersonServices;

public class PersonCollections implements PersonServices {
    private Set<Person> people;
	
	public PersonCollections() {
		people = new HashSet<>();
		
		Person p = new Person();
		p.setId(1);
		p.setUserName("sierra");
		p.setPassword("pass");
		people.add(p);
	}

	@Override
	public Person add(Person t) throws NonUniqueUsernameException {
		for (Person p : people) {
			if (p.getUserName().equals(t.getUserName())) {
                throw new NonUniqueUsernameException();
            }
        }
        t.setId(people.size() + 1);
        people.add(t);
        return t;
    }


    public Person getById(Integer id) {
        for (Person p : people) {
            if (p.getId() == (id))
                return p;
        }
        return null;
    }


    public Person getUsername(String username) {
        for (Person p : people) {
            if (p.getUserName().equals(username))
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
		//return;
	}


	public void delete(Person t) {
		people.remove(t);
	}

	@Override
	public Person createUser(Person p) throws NonUniqueUsernameException {
		return null;
	}

	@Override
	public Person loginUser(String userId) {
		return null;
	}
}
