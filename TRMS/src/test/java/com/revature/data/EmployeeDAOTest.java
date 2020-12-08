package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.Employee;

public class EmployeeDAOTest {

	
	@Test
	public void TestAddDelete()
	{
		EmployeeDAO dao = DAOFactory.getEmployeeDAO();
		
		Employee test = new Employee();
		
		test = dao.add(test);
		
		assertTrue(test.getId() != -1);
		int id = test.getId();
		dao.delete(test);
		assertTrue(dao.getById(id) == null);
	}
	
	@Test
	public void TestGetByID()
	{
		EmployeeDAO dao = DAOFactory.getEmployeeDAO();
		
		Employee test = new Employee();
		
		test = dao.add(test);
		
		Employee get = dao.getById(test.getId());
		assertEquals(get,test);
		
		dao.delete(test);
	}
	
	@Test
	public void TestGetByUsername()
	{
		EmployeeDAO dao = DAOFactory.getEmployeeDAO();
		
		Employee test = new Employee();
		
		test = dao.add(test);
		
		Employee get = dao.getByUserName(test.getUsername());
		assertEquals(get,test);
		dao.delete(test);
	}
	
	@Test
	public void TestUpdate()
	{
		EmployeeDAO dao = DAOFactory.getEmployeeDAO();
		
		Employee test = new Employee();
		
		test = dao.add(test);
		
		test.setName("something");
		
		dao.update(test);
		
		assertEquals(test.getName(),dao.getById(test.getId()).getName());
		
		dao.delete(test);
	}
	
	@Test
	public void TestGetAll()
	{
		EmployeeDAO dao = DAOFactory.getEmployeeDAO();
		
		Employee test = new Employee();
		
		test = dao.add(test);
		
		Set<Employee> set = dao.getAll();
		
		assertTrue(set.contains(test));
		
		dao.delete(test);
	}
}



