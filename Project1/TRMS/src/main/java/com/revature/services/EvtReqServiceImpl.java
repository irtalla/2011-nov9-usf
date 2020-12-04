package com.revature.services;

import java.util.Set;

import com.revature.beans.EvtReq;
import com.revature.beans.Person;
import com.revature.data.EvtReqDAO;
import com.revature.data.EvtReqDAOFactory;
import com.revature.data.PersonDAO;

public class EvtReqServiceImpl implements EvtReqService {
	private EvtReqDAO evtReqDao;
	private PersonDAO personDao;

	
	public EvtReqServiceImpl() {
		EvtReqDAOFactory evtReqDaoFactory = new EvtReqDAOFactory();
		evtReqDao = evtReqDaoFactory.getEvtReqDAO();	
	}
	
	@Override
	public Integer addEvtReq(EvtReq e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EvtReq getEvtReqById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<EvtReq> getEvtReqs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<EvtReq> getAvailableEvtReqs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateEvtReq(EvtReq e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void initEvtReq(Person p, EvtReq e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEvtReq(EvtReq e) {
		// TODO Auto-generated method stub
		
	}
	


}
