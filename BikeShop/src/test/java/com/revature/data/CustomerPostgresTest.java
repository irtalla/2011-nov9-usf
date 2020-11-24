package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.Customer;

public class CustomerPostgresTest {
	public DAOFactory fac = DAOFactory.getDAOFactory();
	

	@Test
	public void testAdd()
	{
		Customer a = new Customer();
		CustomerDAO aDao = DAOFactory.getCustomerDAO();
		Customer b = aDao.add(a);
		Customer c = new Customer();
		assertNotEquals(c,b);
		aDao.delete(b);
	}
	
	@Test
	public void testGetByID()
	{
		Customer a = new Customer();
		CustomerDAO aDao = DAOFactory.getCustomerDAO();
		Customer b = aDao.add(a);
		Customer c = aDao.getById(b.getID());
		assertEquals(b,c);
		aDao.delete(b);
	}
	
	@Test
	public void testGetAll()
	{
		Customer a = new Customer();
		CustomerDAO aDao = DAOFactory.getCustomerDAO();
		Customer b = aDao.add(a);
		
		Set<Customer> results = aDao.getAll();
		//System.out.println(b);
		//System.out.println(results);
		assertTrue(results.contains(b));
		aDao.delete(b);
	}
	
	@Test
	public void testUpdate()
	{
		Customer a = new Customer();
		CustomerDAO aDao = DAOFactory.getCustomerDAO();
		Customer b = aDao.add(a);
		
		b.setName("new name");;
		aDao.update(b);
		
		Customer result = aDao.getById(b.getID());
		assertEquals(result.getName(),"new name");
		aDao.delete(b);
	}
	
	
	@Test
	public void testDelete()
	{
		Customer a = new Customer();
		CustomerDAO aDao = DAOFactory.getCustomerDAO();
		Customer b = aDao.add(a);
		aDao.delete(b);
		
		Set<Customer> results = aDao.getAll();
		
		assertFalse(results.contains(b));
	}
	
	@Test
	public void testGetByLoginCredentials()
	{
		Customer a = new Customer();
		String username = "TestUsername";
		String password = "TestPassword";
		a.setUsername(username);
		a.setPassword(password);
		CustomerDAO aDao = DAOFactory.getCustomerDAO();
		Customer b = aDao.add(a);
		
		Customer c = aDao.getByLoginCredentials(username, password);
		
		assertEquals(b,c);
		aDao.delete(b);
	}
	
	@Test
	public void testVerifyUniqueUsernameNegative()
	{
		Customer a = new Customer();
		String username = "TestUsername";
		String password = "TestPassword";
		a.setUsername(username);
		a.setPassword(password);
		CustomerDAO aDao = DAOFactory.getCustomerDAO();
		Customer b = aDao.add(a);
		
		assertFalse(aDao.verifyUniqueUsername(username));
		aDao.delete(b);
	}
	
	@Test
	public void testVerifyUniqueUsernamePositive()
	{
		Customer a = new Customer();
		String username = "TestUsername";
		String password = "TestPassword";
		a.setUsername(username);
		a.setPassword(password);
		CustomerDAO aDao = DAOFactory.getCustomerDAO();
		//Customer b = aDao.add(a);
		
		assertTrue(aDao.verifyUniqueUsername(username));
		//aDao.delete(b);
	}
}
