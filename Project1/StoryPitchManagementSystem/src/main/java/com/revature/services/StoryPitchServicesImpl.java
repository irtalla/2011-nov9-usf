package com.revature.services;

import java.util.Set;

import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.beans.StoryPitch;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;
import com.revature.data.StoryPitchDAO;
import com.revature.data.StoryPitchDAOFactory;

public class StoryPitchServicesImpl implements StoryPitchServices{

	@Override
	public Integer addStoryPitch(StoryPitch sp) {
		StoryPitchDAOFactory spdf = new StoryPitchDAOFactory();
		StoryPitchDAO storyPitchDao = spdf.getStoryPitchDAO();
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		PersonDAO personDao = personDaoFactory.getPersonDAO();
		
		Integer pid = sp.getPersonID();
		Person author = personDao.getByPersonID(pid);
		Integer points = author.getPoints();
		Integer status = 0;
		//check to see if author has enough points to submit story
		switch (sp.getType().getTypeID()) {
			case 1: if(points >= 50) {
						author.setPoints(points - 50);
					}
			break;
			case 2: if(points >= 25) {
						author.setPoints(points - 25);
					}
			break;
			case 3: if(points >= 20) {
						author.setPoints(points - 20);
					}
			break;
			case 4: if(points >= 10) {
						author.setPoints(points - 10);
					}
			break;
			default:
				break;
		}
		StoryPitch sp2 = storyPitchDao.add(sp);
		//subtract points from author if necessary
		personDao.update(author);
		return sp2.getStoryID();
	}

	@Override
	public StoryPitch getByStoryPitchID(Integer id) {
		StoryPitchDAOFactory spdf = new StoryPitchDAOFactory();
		StoryPitchDAO storyPitchDao = spdf.getStoryPitchDAO();
		return storyPitchDao.getByStoryPitchID(id);
	}

	@Override
	public Set<StoryPitch> getByAuthorID(Integer id) {
		StoryPitchDAOFactory spdf = new StoryPitchDAOFactory();
		StoryPitchDAO storyPitchDao = spdf.getStoryPitchDAO();
		//System.out.println(storyPitchDao.getByAuthorID(id));
		return storyPitchDao.getByAuthorID(id);
	}

	@Override
	public Set<StoryPitch> getByStatusID(Integer id) {
		StoryPitchDAOFactory spdf = new StoryPitchDAOFactory();
		StoryPitchDAO storyPitchDao = spdf.getStoryPitchDAO();
		return storyPitchDao.getByStatusID(id);
	}

	@Override
	public Set<StoryPitch> getByPriorityAndGenreID(Integer pid, Integer gid) {
		StoryPitchDAOFactory spdf = new StoryPitchDAOFactory();
		StoryPitchDAO storyPitchDao = spdf.getStoryPitchDAO();
		return storyPitchDao.getByPriorityAndGenreID(pid, gid);
	}

	@Override
	public Set<StoryPitch> getByStatusAndGenreID(Integer sid, Integer gid) {
		StoryPitchDAOFactory spdf = new StoryPitchDAOFactory();
		StoryPitchDAO storyPitchDao = spdf.getStoryPitchDAO();
		return storyPitchDao.getByStatusAndGenreID(sid, gid);
	}

	@Override
	public Set<StoryPitch> getAll() {
		StoryPitchDAOFactory spdf = new StoryPitchDAOFactory();
		StoryPitchDAO storyPitchDao = spdf.getStoryPitchDAO();
		return storyPitchDao.getAll();
	}
	
	@Override
	public Set<StoryPitch> getByGenreID(Integer id) {
		StoryPitchDAOFactory spdf = new StoryPitchDAOFactory();
		StoryPitchDAO storyPitchDao = spdf.getStoryPitchDAO();
		return null;//storyPitchDao.getByGenreID(id);
	}

	@Override
	public void updateStoryPitch(StoryPitch sp) {
		StoryPitchDAOFactory spdf = new StoryPitchDAOFactory();
		StoryPitchDAO storyPitchDao = spdf.getStoryPitchDAO();
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		PersonDAO personDao = personDaoFactory.getPersonDAO();
		
		storyPitchDao.update(sp);
	}

	@Override
	public void deleteStoryPitch(StoryPitch sp) {
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		PersonDAO personDao = personDaoFactory.getPersonDAO();
		
		StoryPitchDAOFactory storyPitchDaoFactory = new StoryPitchDAOFactory();
		StoryPitchDAO storyPitchDao = storyPitchDaoFactory.getStoryPitchDAO();
		
		Integer status = sp.getStatus().getStatusID();
		Integer points = 0;
		// adjusting author's points if necessary
		//checking if story pitch was active 
		
//		if((status < 7) && (status > 1)) { 
//			switch (sp.getType().getTypeID()) {
//				case 1:
//					points = 50;
//					break;
//				case 2: 
//					points = 25;
//					break;
//				case 3: 
//					points = 20;
//					break;
//				case 4: 
//					points = 10;
//					break;
//				default:
//					break;
//			}
//		}
		//updating author

		Status storyStatus = sp.getStatus();
		storyStatus.setStatusID(status);
		sp.setStatus(storyStatus);
		
		Person author = personDao.getByPersonID(sp.getPersonID());
		author.setPoints((author.getPoints() + points));
		personDao.update(author);
		//adding approval
		storyPitchDao.delete(sp);
	}

}
