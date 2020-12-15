package com.revature.services;

import java.util.Set;

import com.revature.beans.Person;
import com.revature.beans.Request;
import com.revature.data.RequestDAO;
import com.revature.data.RequestDAOFactory;

public class RequestServiceImpl implements RequestService {
	private RequestDAO requestDAO;

	public RequestServiceImpl(){
		RequestDAOFactory requestDAOFactory = new RequestDAOFactory();
		requestDAO = requestDAOFactory.getRequestDAO();
	}

	public Integer addRequest(Request r, Person fromPerson, Person toPerson) {
		r.setFromPerson(fromPerson);
		r.setToPerson(toPerson);
		return requestDAO.add(r).getId();
	}

	public Request getRequestById(Integer id) {
		return requestDAO.getById(id);
	}

	public Set<Request> getRequests() {
		return requestDAO.getAll();
	}

	public Set<Request> getRequestsByPerson(Person p) {
		return requestDAO.getRequestsByPerson(p);
	}

	public void updateRequest(Request r) {
		requestDAO.update(r);
	}

	public void removeRequest(Request r) {
		requestDAO.delete(r);
	}

}
