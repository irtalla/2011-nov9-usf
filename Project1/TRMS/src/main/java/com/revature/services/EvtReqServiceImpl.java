package com.revature.services;

import java.util.Set;

import com.revature.beans.EvtReq;
import com.revature.beans.Person;
import com.revature.data.EvtReqDAO;
import com.revature.data.EvtReqDAOFactory;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;

public class EvtReqServiceImpl implements EvtReqService {
	private EvtReqDAO evtReqDao;
	private PersonDAO personDao;

	
	public EvtReqServiceImpl() {
		EvtReqDAOFactory evtReqDaoFactory = new EvtReqDAOFactory();
		evtReqDao = evtReqDaoFactory.getEvtReqDAO();	
		PersonDAOFactory pdf = new PersonDAOFactory();
		personDao = pdf.getPersonDAO();
	}
	
	@Override		
	public Integer addEvtReq(EvtReq e, Person p) {
    	
		return evtReqDao.add(e).getId();
	}
	
	@Override
	public EvtReq addEvtReq2(EvtReq e) {
		return evtReqDao.add(e);
	} 
	
	@Override
	public EvtReq getEvtReqById(Integer id) {
		return evtReqDao.getById(id);
	}

	@Override
	public boolean approveEvtReq(Integer id, String username) {
		return evtReqDao.approveEvtReq(id, username);
	}
	
	@Override
	public Set<EvtReq> getEvtReqs() {
		return evtReqDao.getAll();		
	}

	@Override
	public Set<EvtReq> getAvailableEvtReqs() {
		 return evtReqDao.getAvailableEvents();
	}

	@Override
	public void updateEvtReq(EvtReq e) {
		evtReqDao.update(e);  
		
	}
	@Override
	public Set<EvtReq> getPendingEvtReqs(){
		return evtReqDao.getPendingEventRequest();
	}
	@Override
	public void initEvtReq(Person p, EvtReq e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEvtReq(EvtReq e) {
		evtReqDao.delete(e);
		
	}
	


}
