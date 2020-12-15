package com.revature.services;

import java.util.Set;



import com.revature.beans.Approval;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.beans.StoryPitch;
import com.revature.data.ApprovalDAO;
import com.revature.data.ApprovalDAOFactory;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;
import com.revature.data.StoryPitchDAO;
import com.revature.data.StoryPitchDAOFactory;

public class ApprovalServicesImpl implements ApprovalServices{

	@Override
	public Integer addApproval(Approval a) {
		ApprovalDAOFactory approvalDaoFactory = new ApprovalDAOFactory();
		ApprovalDAO approvalDao = approvalDaoFactory.getApprovalDAO();

		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		PersonDAO personDao = personDaoFactory.getPersonDAO();
		
		StoryPitchDAOFactory storyPitchDaoFactory = new StoryPitchDAOFactory();
		StoryPitchDAO storyPitchDao = storyPitchDaoFactory.getStoryPitchDAO();
		
		StoryPitch sp = storyPitchDao.getByStoryPitchID(a.getStoryApprovedID());
		Status storyStatus = sp.getStatus();
		Integer status = sp.getStatus().getStatusID();
		Integer points = 0;
		// adjusting author's points if necessary
		//checking if story pitch was active and is being updated to an inactive (accepted for publication) state
		if((status < 7) && (status > 1) && (a.getStatusApproved() == 7 )) { 
			switch (sp.getType().getTypeID()) {
				case 1:
					points = 50;
					break;
				case 2: 
					points = 25;
					break;
				case 3: 
					points = 20;
					break;
				case 4: 
					points = 10;
					break;
				default:
					break;
			}
		}
		//updating story pitch
//		storyStatus.setStatusID(a.getStatusApproved());
//		sp.setStatus(storyStatus);
//		storyPitchDao.update(sp);
		//updating author
		Person author = personDao.getByPersonID(sp.getPersonID());
		author.setPoints((author.getPoints() + points));
		personDao.update(author);
		//adding approval
		return approvalDao.add(a).getApprovalID();
	}

	@Override
	public Approval getByApprovalID(Integer id) {
		ApprovalDAOFactory approvalDaoFactory = new ApprovalDAOFactory();
		ApprovalDAO approvalDao = approvalDaoFactory.getApprovalDAO();
		return approvalDao.getByApprovalID(id);
	}

	@Override
	public Set<Approval> getByApprovedID(Integer id) {
		ApprovalDAOFactory approvalDaoFactory = new ApprovalDAOFactory();
		ApprovalDAO approvalDao = approvalDaoFactory.getApprovalDAO();
		return approvalDao.getByApprovedID(id);
	}

	@Override
	public Set<Approval> getAll() {
		ApprovalDAOFactory approvalDaoFactory = new ApprovalDAOFactory();
		ApprovalDAO approvalDao = approvalDaoFactory.getApprovalDAO();
		return approvalDao.getAll();
	}

	@Override
	public void updateApproval(Approval a) {
		ApprovalDAOFactory approvalDaoFactory = new ApprovalDAOFactory();
		ApprovalDAO approvalDao = approvalDaoFactory.getApprovalDAO();
		approvalDao.update(a);
		
	}

	@Override
	public void deleteApproval(Approval a) {
		ApprovalDAOFactory approvalDaoFactory = new ApprovalDAOFactory();
		ApprovalDAO approvalDao = approvalDaoFactory.getApprovalDAO();
		approvalDao.delete(a);
	}

}
