package com.revature.data;

import java.util.Set;

import com.revature.beans.Person;
import com.revature.beans.Request;
import com.revature.exceptions.NonUniqueUsernameException;

public interface RequestDAO extends GenericDAO<Request> {
	public Request add(Request r) throws NonUniqueUsernameException;
	public void updateRequest(Request r);
	public Set<Request> getRequestByUsername(String username);
	public Set<Request> getAll();
}
