package com.revature.services;

import java.util.Set;

import com.revature.beans.InformationRequest;
import com.revature.data.DAOFactory;
import com.revature.data.InformationRequestDAO;

public class InformationRequestServiceImpl implements InformationRequestService {
	InformationRequestDAO dao = DAOFactory.getInformationRequestDAO();

	@Override
	public Integer addInformationRequest(InformationRequest e) {
		return dao.add(e).getId();
	}

	@Override
	public InformationRequest getInformationRequestById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public Set<InformationRequest> getInformationRequests() {
		return dao.getAll();
	}

	@Override
	public void updateInformationRequest(InformationRequest e) {
		dao.update(e);

	}

	@Override
	public void removeInformationRequest(InformationRequest e) {
		dao.delete(e);
	}

}
