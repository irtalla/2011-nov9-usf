package com.revature.services;

import java.util.Set;

import com.cross.beans.Request;
import com.revature.data.RequestDAO;
import com.revature.data.RequestHibernate;

public class RequestServiceImpl implements RequestService {

	RequestDAO requestDAO;
	
	public RequestServiceImpl() {
		requestDAO = new RequestHibernate(); 
	}

	@Override
	public Set<Request> getRequestsByParticipantId(Integer id) {
		return requestDAO.getByParticipantId(id);
	}
}
