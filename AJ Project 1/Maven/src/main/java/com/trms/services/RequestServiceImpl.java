package com.trms.services;

import java.util.List;

import com.trms.beans.Request;
import com.trms.data.RequestDAO;
import com.trms.data.RequestDAOFactory;

public class RequestServiceImpl implements RequestService {
	private RequestDAO requestDao;
	
	public RequestServiceImpl() {
		RequestDAOFactory rdf = new RequestDAOFactory();
		requestDao = rdf.getRequestDAO();
	}

	@Override
	public Integer addRequest(Request r) {
		 
		return requestDao.add(r).getId();
	}

	@Override
	public Request getById(Integer id) {
		
		return requestDao.getById(id);
	}

	@Override
	public List<Request> getByPersonId(Integer id) {
		// TODO Auto-generated method stub
		return requestDao.getByRequestorId(id);
	}

	@Override
	public List<Request> getByManagerId(Integer id) {
		// TODO Auto-generated method stub
		return requestDao.getByManagerId(id);
	}

	@Override
	public List<Request> getBydHeadId(Integer id) {
		// TODO Auto-generated method stub
		return requestDao.getBydHeadId(id);
	}

	@Override
	public boolean update(Request r) {
		requestDao.update(r);
		return true;
	}

	@Override
	public List<Request> getAll() {
		return requestDao.getAll();
	}

}
