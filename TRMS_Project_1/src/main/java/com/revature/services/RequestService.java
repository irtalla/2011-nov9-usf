package com.revature.services;

import java.util.Set;

import com.revature.beans.Request;
import com.revature.exceptions.NonUniqueUsernameException;

public interface RequestService {
	// create
		public Integer addRequest(Request r) throws NonUniqueUsernameException;
		// read
		public Request getRequestById(Integer id);
		public Set<Request> getRequests();
		public Set<Request> getRequestByUsername(String username);
		// update
		public void updateRequest(Request r);
		// delete
		public void deleteRequest(Request r);

	
}
