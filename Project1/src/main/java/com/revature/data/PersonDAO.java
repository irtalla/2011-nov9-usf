package com.revature.data;

import com.revature.beans.Request;
import java.util.Set;

import com.revature.beans.Person;
import com.revature.exceptions.NonUniqueUsernameException;

public interface PersonDAO extends GenericDAO<Person>{
	public Person add(Person p) throws NonUniqueUsernameException;
	public Person getByUsername(String username);
	public Set<Request> getRequests(Person p);
}
