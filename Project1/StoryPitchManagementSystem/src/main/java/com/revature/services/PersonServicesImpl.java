package com.revature.services;

import java.util.Set;

import com.revature.beans.Approval;
import com.revature.beans.ChangeRequest;
import com.revature.beans.InfoRequest;
import com.revature.beans.InfoResponse;
import com.revature.beans.Person;
import com.revature.beans.Rejection;
import com.revature.beans.StoryPitch;
import com.revature.data.ApprovalDAO;
import com.revature.data.ApprovalDAOFactory;
import com.revature.data.ChangeRequestDAO;
import com.revature.data.ChangeRequestDAOFactory;
import com.revature.data.InfoRequestDAO;
import com.revature.data.InfoRequestDAOFactory;
import com.revature.data.InfoResponseDAO;
import com.revature.data.InfoResponseDAOFactory;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;
import com.revature.data.RejectionDAO;
import com.revature.data.RejectionDAOFactory;
import com.revature.data.StoryPitchDAO;
import com.revature.data.StoryPitchDAOFactory;

public class PersonServicesImpl implements PersonServices{
	
	//private PersonDAO personDao;
	
	public PersonServicesImpl() {
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		PersonDAO personDao = personDaoFactory.getPersonDAO();
		//System.out.println(personDao == null);
	}

	@Override
	public Integer addPerson(Person p) {
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		PersonDAO personDao = personDaoFactory.getPersonDAO();
		personDao.add(p);
		return p.getPersonID();
	}

	@Override
	public Person getByPersonID(Integer id) {
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		PersonDAO personDao = personDaoFactory.getPersonDAO();
		
		ApprovalDAOFactory approvalDaoFactory = new ApprovalDAOFactory();
		ApprovalDAO approvalDao = approvalDaoFactory.getApprovalDAO();

		RejectionDAOFactory rejectionDaoFactory = new RejectionDAOFactory();
		RejectionDAO rejectionDao = rejectionDaoFactory.getRejectionDAO();

		StoryPitchDAOFactory storyPitchDaoFactory = new StoryPitchDAOFactory();
		StoryPitchDAO storyPitchDao = storyPitchDaoFactory.getStoryPitchDAO();

		InfoRequestDAOFactory infoRequestDAOFactory = new InfoRequestDAOFactory();
		InfoRequestDAO infoRequestDao = infoRequestDAOFactory.getInfoRequestDAO();

		InfoResponseDAOFactory infoResponseDaoFactory = new InfoResponseDAOFactory();
		InfoResponseDAO infoResponseDao = infoResponseDaoFactory.getInfoResponseDAO();

		ChangeRequestDAOFactory changeRequestDAOFactory = new ChangeRequestDAOFactory();
		ChangeRequestDAO changeRequestDao = changeRequestDAOFactory.getChangeRequestDAO();

		
		//getting person
		Person p = personDao.getByPersonID(id);
		if(p != null) {
			Set<StoryPitch> sps = storyPitchDao.getByAuthorID(id);
			p.setStoryPitches(sps);
			Set<Approval> approvals = approvalDao.getByApprovedID(id);
			p.setApprovals(approvals);
			Set<Rejection> rejections = rejectionDao.getByRejectedID(id);
			p.setRejections(rejections);
			Set<InfoRequest> infoRequests = infoRequestDao.getByRequestedID(id);
			p.setInfoRequests(infoRequests);
			if(p.getRoleID() < 4) {
				Set<InfoResponse> infoResponses = infoResponseDao.getByRespondedID(id);
				p.setInfoResponses(infoResponses);
			}
			else {
				//System.out.println("Setting responses for an author");
				Set<InfoResponse> infoResponses = infoResponseDao.getByRespondingID(id);
				p.setInfoResponses(infoResponses);
				
			}
			Set<ChangeRequest> changeRequests = changeRequestDao.getByRequestedID(id);
			p.setChangeRequests(changeRequests);
			//System.out.println("returning person: " + p);
		}
		return p;
	}

	@Override
	public Person getByUsername(String username) {
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		PersonDAO personDao = personDaoFactory.getPersonDAO();
		
		ApprovalDAOFactory approvalDaoFactory = new ApprovalDAOFactory();
		ApprovalDAO approvalDao = approvalDaoFactory.getApprovalDAO();
	
		RejectionDAOFactory rejectionDaoFactory = new RejectionDAOFactory();
		RejectionDAO rejectionDao = rejectionDaoFactory.getRejectionDAO();
	
		StoryPitchDAOFactory storyPitchDaoFactory = new StoryPitchDAOFactory();
		StoryPitchDAO storyPitchDao = storyPitchDaoFactory.getStoryPitchDAO();
	
		InfoRequestDAOFactory infoRequestDAOFactory = new InfoRequestDAOFactory();
		InfoRequestDAO infoRequestDao = infoRequestDAOFactory.getInfoRequestDAO();
	
		InfoResponseDAOFactory infoResponseDaoFactory = new InfoResponseDAOFactory();
		InfoResponseDAO infoResponseDao = infoResponseDaoFactory.getInfoResponseDAO();
	
		ChangeRequestDAOFactory changeRequestDAOFactory = new ChangeRequestDAOFactory();
		ChangeRequestDAO changeRequestDao = changeRequestDAOFactory.getChangeRequestDAO();
	
		
		//getting person
		Person p = personDao.getByUsername(username);
		Integer id = p.getPersonID();
		
		Set<StoryPitch> sps = storyPitchDao.getByAuthorID(id);
		p.setStoryPitches(sps);
		Set<Approval> approvals = approvalDao.getByApprovedID(id);
		p.setApprovals(approvals);
		Set<Rejection> rejections = rejectionDao.getByRejectedID(id);
		p.setRejections(rejections);
		Set<InfoRequest> infoRequests = infoRequestDao.getByRequestedID(id);
		p.setInfoRequests(infoRequests);
		if(p.getRoleID() < 4) {
			Set<InfoResponse> infoResponses = infoResponseDao.getByRespondedID(id);
			p.setInfoResponses(infoResponses);
		}
		else {
			//System.out.println("Setting responses for an author");
			Set<InfoResponse> infoResponses = infoResponseDao.getByRespondingID(id);
			p.setInfoResponses(infoResponses);
			
		}
		Set<ChangeRequest> changeRequests = changeRequestDao.getByRequestedID(id);
		p.setChangeRequests(changeRequests);
		return p;
	}

	@Override
	public void updatePerson(Person p) {
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		PersonDAO personDao = personDaoFactory.getPersonDAO();
		personDao.update(p);
		
	}

	@Override
	public void deletePerson(Person p) {
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		PersonDAO personDao = personDaoFactory.getPersonDAO();
		personDao.delete(p);
		
	}

//	@Override
//	public Set<Person> getByRoleID(Integer id) {
//		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
//		PersonDAO personDao = personDaoFactory.getPersonDAO();
//		//System.out.println(pPersonDao == null);
//		return personDao.getByRoleId(id);
//	}

}
