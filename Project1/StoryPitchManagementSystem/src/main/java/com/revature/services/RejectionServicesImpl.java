package com.revature.services;

import java.util.Set;

import com.revature.beans.Rejection;
import com.revature.beans.Status;
import com.revature.beans.StoryPitch;
import com.revature.data.RejectionDAO;
import com.revature.data.RejectionDAOFactory;
import com.revature.data.StoryPitchDAO;
import com.revature.data.StoryPitchDAOFactory;

public class RejectionServicesImpl implements RejectionServices{

	@Override
	public Integer addRejection(Rejection r) {
		RejectionDAOFactory rejectionDaoFactory = new RejectionDAOFactory();
		RejectionDAO rejectionDao = rejectionDaoFactory.getRejectionDAO();

		StoryPitchDAOFactory storyPitchDaoFactory = new StoryPitchDAOFactory();
		StoryPitchDAO storyPitchDao = storyPitchDaoFactory.getStoryPitchDAO();
		StoryPitch sp = storyPitchDao.getByStoryPitchID(r.getStoryRejectedID());
		
		Status storyStatus = sp.getStatus();
		storyStatus.setStatusID(8); // 8 is hard coded in the SQL database as the status for rejected stories
		sp.setStatus(storyStatus);
		storyPitchDao.update(sp);
		
		return rejectionDao.add(r).getRejectionID();
	}

	@Override
	public Rejection getByRejectionID(Integer id) {
		RejectionDAOFactory rejectionDaoFactory = new RejectionDAOFactory();
		RejectionDAO rejectionDao = rejectionDaoFactory.getRejectionDAO();
		return rejectionDao.getByRejectionID(id);
	}

	@Override
	public Set<Rejection> getByRejectedID(Integer id) {
		RejectionDAOFactory rejectionDaoFactory = new RejectionDAOFactory();
		RejectionDAO rejectionDao = rejectionDaoFactory.getRejectionDAO();
		return rejectionDao.getByRejectedID(id);
	}

	@Override
	public Set<Rejection> getAll() {
		RejectionDAOFactory rejectionDaoFactory = new RejectionDAOFactory();
		RejectionDAO rejectionDao = rejectionDaoFactory.getRejectionDAO();
		return rejectionDao.getAll();
	}

	@Override
	public void updateRejection(Rejection r) {
		RejectionDAOFactory rejectionDaoFactory = new RejectionDAOFactory();
		RejectionDAO rejectionDao = rejectionDaoFactory.getRejectionDAO();
		rejectionDao.update(r);
		
	}

	@Override
	public void deleteRejection(Rejection r) {
		RejectionDAOFactory rejectionDaoFactory = new RejectionDAOFactory();
		RejectionDAO rejectionDao = rejectionDaoFactory.getRejectionDAO();
		rejectionDao.delete(r);
	}

}