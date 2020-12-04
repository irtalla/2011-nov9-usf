package com.revature.data;

import java.util.Set;

import com.revature.beans.EvtReq;
import com.revature.beans.Person;

public interface EvtReqDAO extends GenericDAO<EvtReq> {
	public EvtReq add(EvtReq e);
	public Set<EvtReq> getAvailableEvents();
	public void addEvent(Person p, EvtReq e);

}
