package com.revature.data;

import java.util.Set;

import com.revature.beans.EventAttatchment;

public interface AttatchmentDAO extends GenericDAO<EventAttatchment> {
	public Set<EventAttatchment> getEventAttatchmentByEventId(Integer id);

}
