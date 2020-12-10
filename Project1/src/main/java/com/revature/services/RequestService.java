package com.revature.services;

import java.util.Set;

import com.cross.beans.Request;

public interface RequestService {
	
	public Set<Request> getRequestsByParticipantId(Integer personId); 
}
