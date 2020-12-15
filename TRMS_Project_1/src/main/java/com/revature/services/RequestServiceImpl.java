package com.revature.services;

import java.util.Set;

import com.revature.beans.Request;
import com.revature.data.RequestDAO;
import com.revature.data.RequestDAOFactory;
import com.revature.exceptions.NonUniqueUsernameException;

public class RequestServiceImpl implements RequestService {
	private RequestDAO requestDao;
	
	public RequestServiceImpl() {
		RequestDAOFactory requestDAOFactory = new RequestDAOFactory();
		requestDao = requestDAOFactory.getRequestDAO();
	}

	@Override
	public Integer addRequest(Request r) throws NonUniqueUsernameException {
		return requestDao.add(r).getId();
	}

	@Override
	public Request getRequestById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Request> getRequestByUsername(String username) {
		return requestDao.getRequestByUsername(username);
	}

	@Override
	public void updateRequest(Request r) {
		requestDao.updateRequest(r);

	}

	@Override
	public void deleteRequest(Request r) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Request> getRequests() {
		return requestDao.getAll();
	}

}
