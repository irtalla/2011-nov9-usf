package com.revature.services;

import com.revature.beans.Person;

import java.util.Set;

import com.revature.beans.EvtReq;

import exceptions.NonUniqueUsernameException;

public interface PersonService {
		// create
		public Integer addPerson(Person p) throws NonUniqueUsernameException;
		public Person getPersonById(Integer id);
		public Person getPersonByUsername(String username);
		// update
		public void updatePerson(Person p);
		// delete
		public void deletePerson(Person p);
		public boolean isApprover(Integer person_id);
		
		public Set<EvtReq> getEventsByPersonId (Integer person_id);
		
}
