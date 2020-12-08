package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.Event;

public class EventDAOTest {

	@Test
	public void TestAddDelete()
	{
		EventDAO dao = DAOFactory.getEventDAO();
		
		Event test = new Event();
		
		test = dao.add(test);
		
		assertTrue(test.getId() != -1);
		int id = test.getId();
		dao.delete(test);
		assertTrue(dao.getById(id) == null);
	}
	
	@Test
	public void TestGetByID()
	{
		EventDAO dao = DAOFactory.getEventDAO();
		
		Event test = new Event();
		
		test = dao.add(test);
		
		Event get = dao.getById(test.getId());
		assertEquals(get,test);
		
		dao.delete(test);
	}
	@Test
	public void TestUpdate()
	{
		EventDAO dao = DAOFactory.getEventDAO();
		
		Event test = new Event();
		
		test = dao.add(test);
		
		test.setLocation("a place");
		
		dao.update(test);
		
		assertEquals(test.getLocation(),dao.getById(test.getId()).getLocation());
		
		dao.delete(test);
	}
	
	@Test
	public void TestGetAll()
	{
		EventDAO dao = DAOFactory.getEventDAO();
		
		Event test = new Event();
		
		test = dao.add(test);
		
		Set<Event> set = dao.getAll();
		
		assertTrue(set.contains(test));
		
		dao.delete(test);
	}
}
