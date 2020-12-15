package com.revature.services;

import java.util.Set;

import com.revature.beans.InfoRequest;
import com.revature.data.InfoRequestDAO;
import com.revature.data.InfoRequestDAOFactory;
import com.revature.data.InfoRequestPostgreSQL;

public class InfoRequestServicesImpl implements InfoRequestServices{

	@Override
	public Integer addInfoRequest(InfoRequest cr) {
		InfoRequestDAOFactory infoRequestDAOFactory = new InfoRequestDAOFactory();
		InfoRequestDAO infoRequestDao = infoRequestDAOFactory.getInfoRequestDAO();
		return infoRequestDao.add(cr).getRequestID();
	}

	@Override
	public InfoRequest getByRequestID(Integer id) {
		InfoRequestDAOFactory infoRequestDAOFactory = new InfoRequestDAOFactory();
		InfoRequestDAO infoRequestDao = infoRequestDAOFactory.getInfoRequestDAO();
		return infoRequestDao.getByRequestID(id);
	}

	@Override
	public Set<InfoRequest> getAll() {
		InfoRequestDAOFactory infoRequestDAOFactory = new InfoRequestDAOFactory();
		InfoRequestDAO infoRequestDao = infoRequestDAOFactory.getInfoRequestDAO();
		return (Set<InfoRequest>) infoRequestDao.getAll();
	}

	@Override
	public Set<InfoRequest> getByRequestedID(Integer id) {
		InfoRequestDAOFactory infoRequestDAOFactory = new InfoRequestDAOFactory();
		InfoRequestDAO infoRequestDao = infoRequestDAOFactory.getInfoRequestDAO();
		return (Set<InfoRequest>) infoRequestDao.getByRequestedID(id);
	}

	@Override
	public void updateInfoRequest(InfoRequest cr) {
		InfoRequestDAOFactory infoRequestDAOFactory = new InfoRequestDAOFactory();
		InfoRequestDAO infoRequestDao = infoRequestDAOFactory.getInfoRequestDAO();
		infoRequestDao.update(cr);
	}

	@Override
	public void deleteInfoRequest(InfoRequest cr) {
		InfoRequestDAOFactory infoRequestDAOFactory = new InfoRequestDAOFactory();
		InfoRequestDAO infoRequestDao = infoRequestDAOFactory.getInfoRequestDAO();
		infoRequestDao.delete(cr);
		
	}

}
