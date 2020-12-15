package com.revature.services;

import java.util.Set;

import com.revature.beans.Person;
import com.revature.beans.Request;

public interface RequestService {
	// create
	public Integer addRequest(Request r, Person fromPerson, Person toPerson);
	// read
	public Request getRequestById(Integer id);
	public Set<Request> getRequests();
	public Set<Request> getRequestsByPerson(Person p);
	// update
	public void updateRequest(Request r);
	// delete
	public void removeRequest(Request r);
}
