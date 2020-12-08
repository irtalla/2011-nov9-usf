package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.EventType;

public class EventTypeDAOTest {

	@Test
	public void testGetById()
	{
		EventType e = DAOFactory.getEventTypeDAO().getById(1);
		System.out.println(e);
		assertTrue(e != null);
	}
	
	@Test
	public void testGetAll()
	{
		Set<EventType> e = DAOFactory.getEventTypeDAO().getAll();
		assertTrue(e.size() > 0);
		System.out.println(e);
	}
}
