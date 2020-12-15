package com.revature.services;

import java.util.Set;

import com.revature.beans.ChangeRequest;
import com.revature.beans.InfoResponse;
import com.revature.data.ChangeRequestDAO;
import com.revature.data.ChangeRequestPostgreSQL;
import com.revature.data.InfoResponseDAO;
import com.revature.data.InfoResponseDAOFactory;

public class InfoResponseServicesImpl implements InfoResponseServices {

	@Override
	public Integer addInfoResponse(InfoResponse r) {
		InfoResponseDAOFactory infoResponseDaoFactory = new InfoResponseDAOFactory();
		InfoResponseDAO infoResponseDao = infoResponseDaoFactory.getInfoResponseDAO();
		return infoResponseDao.add(r).getRequestID();
	}

	@Override
	public InfoResponse getByResponseID(Integer id) {
		InfoResponseDAOFactory infoResponseDaoFactory = new InfoResponseDAOFactory();
		InfoResponseDAO infoResponseDao = infoResponseDaoFactory.getInfoResponseDAO();
		return infoResponseDao.getByResponseID(id);
	}

	@Override
	public Set<InfoResponse> getByRespondedID(Integer id) {
		InfoResponseDAOFactory infoResponseDaoFactory = new InfoResponseDAOFactory();
		InfoResponseDAO infoResponseDao = infoResponseDaoFactory.getInfoResponseDAO();
		return infoResponseDao.getByRespondedID(id);
	}

	@Override
	public Set<InfoResponse> getAll() {
		InfoResponseDAOFactory infoResponseDaoFactory = new InfoResponseDAOFactory();
		InfoResponseDAO infoResponseDao = infoResponseDaoFactory.getInfoResponseDAO();
		return infoResponseDao.getAll();
	}

	@Override
	public void updateInfoResponse(InfoResponse r) {
		InfoResponseDAOFactory infoResponseDaoFactory = new InfoResponseDAOFactory();
		InfoResponseDAO infoResponseDao = infoResponseDaoFactory.getInfoResponseDAO();
		infoResponseDao.update(r);
	}

	@Override
	public void deleteInfoResponse(InfoResponse r) {
		InfoResponseDAOFactory infoResponseDaoFactory = new InfoResponseDAOFactory();
		InfoResponseDAO infoResponseDao = infoResponseDaoFactory.getInfoResponseDAO();
		infoResponseDao.delete(r);
	}

}
