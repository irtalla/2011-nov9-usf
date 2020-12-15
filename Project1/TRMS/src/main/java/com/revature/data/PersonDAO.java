package com.revature.data;

import java.util.Set;

import com.revature.beans.EvtReq;
import com.revature.beans.Person;

import exceptions.NonUniqueUsernameException;

public interface PersonDAO extends GenericDAO <Person> {
	public Person add(Person p) throws NonUniqueUsernameException;
	public Person getByUsername(String username);
	public boolean isApprover(Integer person_id);
	
	public Set<EvtReq> getEventsByPersonId(Integer id) ;
}
