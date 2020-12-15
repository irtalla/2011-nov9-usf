package com.trms.data;

import com.trms.beans.EventType;

public interface EventTypeDAO extends GenericDAO<EventType> {
	public EventType add (EventType e);
	public EventType getByName(String name);

}
