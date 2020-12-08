package com.revature.services;

import java.util.Set;

import com.revature.beans.Event;
import com.revature.beans.EventType;

public interface EventService {

	public Integer addEvent(Event e);
	// "read" methods
	public Event getEventById(Integer id);
	public Set<Event> getEvents();
	public EventType getEventTypeById(Integer id);
	public Set<EventType> getEventTypes();
	// "update" methods
	public void updateEvent(Event e);
	// "delete" methods
	public void removeEvent(Event e);
}
