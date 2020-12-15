package com.revature.services;

import java.util.Set;

import com.revature.beans.ChangeRequest;
import com.revature.beans.StoryPitch;
import com.revature.data.ChangeRequestDAO;
import com.revature.data.ChangeRequestDAOFactory;
import com.revature.data.ChangeRequestPostgreSQL;
import com.revature.data.StoryPitchDAO;
import com.revature.data.StoryPitchDAOFactory;

public class ChangeRequestServicesImpl implements ChangeRequestServices{

	@Override
	public Integer addChangeRequest(ChangeRequest cr) {
		ChangeRequestDAOFactory changeRequestDaoFactory = new ChangeRequestDAOFactory();
		ChangeRequestDAO changeRequestDao = changeRequestDaoFactory.getChangeRequestDAO();
		

		StoryPitchDAOFactory storyPitchDaoFactory = new StoryPitchDAOFactory();
		StoryPitchDAO storyPitchDao = storyPitchDaoFactory.getStoryPitchDAO();
		
		StoryPitch sp = storyPitchDao.getByStoryPitchID(cr.getStoryID());
		sp.setChangesRequested(1);
		storyPitchDao.update(sp);
		return changeRequestDao.add(cr).getRequestID();
	}

	@Override
	public ChangeRequest getByRequestID(Integer id) {
		ChangeRequestDAOFactory changeRequestDaoFactory = new ChangeRequestDAOFactory();
		ChangeRequestDAO changeRequestDao = changeRequestDaoFactory.getChangeRequestDAO();
		return  changeRequestDao.getByRequestID(id);
	}

	@Override
	public Set<ChangeRequest> getByAuthorID(Integer id) {
		ChangeRequestDAOFactory changeRequestDaoFactory = new ChangeRequestDAOFactory();
		ChangeRequestDAO changeRequestDao = changeRequestDaoFactory.getChangeRequestDAO();
		return changeRequestDao.getByRequestedID(id);
	}

	@Override
	public Set<ChangeRequest> getAll() {
		ChangeRequestDAOFactory changeRequestDaoFactory = new ChangeRequestDAOFactory();
		ChangeRequestDAO changeRequestDao = changeRequestDaoFactory.getChangeRequestDAO();
		return changeRequestDao.getAll();
	}

	@Override
	public void updateChangeRequest(ChangeRequest cr) {
		ChangeRequestDAOFactory changeRequestDaoFactory = new ChangeRequestDAOFactory();
		ChangeRequestDAO changeRequestDao = changeRequestDaoFactory.getChangeRequestDAO();
		changeRequestDao.update(cr);
	}

	@Override
	public void deleteChangeRequest(ChangeRequest cr) {
		ChangeRequestDAOFactory changeRequestDaoFactory = new ChangeRequestDAOFactory();
		ChangeRequestDAO changeRequestDao = changeRequestDaoFactory.getChangeRequestDAO();
		changeRequestDao.delete(cr);
		
	}

}
