package com.revature.data;

import java.util.Set;

import com.revature.beans.EvtReq;
import com.revature.beans.Person;

public interface EvtReqDAO extends GenericDAO<EvtReq> {
	public EvtReq add(EvtReq e);
	public Set<EvtReq> getAvailableEvents();
	public void addEvent(Person p, EvtReq e);
	public Set<EvtReq> getEventsByPersonId(Integer person_id);
	public Set<EvtReq> getPendingEventRequest();
	public boolean approveEvtReq(Integer id, String username);
}
