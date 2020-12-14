package com.revature.services;

import java.util.Set;

import com.revature.beans.Event;
import com.revature.beans.EventAttatchment;
import com.revature.beans.EventType;
import com.revature.data.DAOFactory;

public class EventServiceImpl implements EventService {

	@Override
	public Integer addEvent(Event e) {
		return DAOFactory.getEventDAO().add(e).getId();
	}

	@Override
	public Event getEventById(Integer id) {
		return DAOFactory.getEventDAO().getById(id);
	}

	@Override
	public Set<Event> getEvents() {
		return DAOFactory.getEventDAO().getAll();
	}

	@Override
	public void updateEvent(Event e) {
		DAOFactory.getEventDAO().update(e);

	}

	@Override
	public void removeEvent(Event e) {
		DAOFactory.getEventDAO().delete(e);

	}

	@Override
	public EventType getEventTypeById(Integer id) {
		return DAOFactory.getEventTypeDAO().getById(id);
	}

	@Override
	public Set<EventType> getEventTypes() {
		return DAOFactory.getEventTypeDAO().getAll();
	}

	@Override
	public Integer addEventAttatchment(EventAttatchment e) {
		return DAOFactory.getAttatchmentDAO().add(e).getId();
	}

	@Override
	public Set<EventAttatchment> getEventAttatchmentsByEventId(Integer id) {
		return DAOFactory.getAttatchmentDAO().getEventAttatchmentByEventId(id);
	}

	@Override
	public void removeEventAttatchment(EventAttatchment e) {
		DAOFactory.getAttatchmentDAO().delete(e);
		
	}

}
