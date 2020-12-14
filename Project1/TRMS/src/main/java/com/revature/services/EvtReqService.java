package com.revature.services;

import java.util.Set;

import com.revature.beans.EvtReq;
import com.revature.beans.Person;

public interface EvtReqService {
	public Integer addEvtReq(EvtReq e, Person p);
	public EvtReq addEvtReq2(EvtReq e);
	
	// "read" methods
	public EvtReq getEvtReqById(Integer id);
	public boolean approveEvtReq(Integer id, String username);
	public Set<EvtReq> getEvtReqs();
	public Set<EvtReq> getAvailableEvtReqs();
	public Set<EvtReq> getPendingEvtReqs();
	// "update" methods
	public void updateEvtReq(EvtReq e);
	public void initEvtReq(Person p, EvtReq e);
	// "delete" methods
	public void removeEvtReq(EvtReq e);

}
