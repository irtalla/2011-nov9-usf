package com.revature.data;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;


import org.junit.jupiter.api.Test;

import com.revature.beans.Accessory;
import com.revature.beans.Customer;

public class AccessoryPostgresTest {
	public DAOFactory fac = DAOFactory.getDAOFactory();
	

	@Test
	public void testAdd()
	{
		Accessory a = new Accessory();
		AccessoryDAO aDao = DAOFactory.getAccessoryDAO();
		Accessory b = aDao.add(a);
		Accessory c = new Accessory();
		assertNotEquals(c,b);
		aDao.delete(b);
	}
	
	@Test
	public void testGetByID()
	{
		Accessory a = new Accessory();
		AccessoryDAO aDao = DAOFactory.getAccessoryDAO();
		Accessory b = aDao.add(a);
		Accessory c = aDao.getById(b.getID());
		assertEquals(b,c);
		aDao.delete(b);
	}
	
	@Test
	public void testGetAll()
	{
		Accessory a = new Accessory();
		AccessoryDAO aDao = DAOFactory.getAccessoryDAO();
		Accessory b = aDao.add(a);
		
		Set<Accessory> results = aDao.getAll();
		
		assertTrue(results.contains(b));
		aDao.delete(b);
	}
	
	@Test
	public void testUpdate()
	{
		Accessory a = new Accessory();
		AccessoryDAO aDao = DAOFactory.getAccessoryDAO();
		Accessory b = aDao.add(a);
		
		b.setName("new name");
		aDao.update(b);
		
		Accessory result = aDao.getById(b.getID());
		assertEquals(result.getName(),"new name");
		aDao.delete(b);
	}
	
	
	@Test
	public void testDelete()
	{
		Accessory a = new Accessory();
		AccessoryDAO aDao = DAOFactory.getAccessoryDAO();
		Accessory b = aDao.add(a);
		aDao.delete(b);
		
		Set<Accessory> results = aDao.getAll();
		
		assertFalse(results.contains(b));
	}
	
	@Test
	public void testPurchase()
	{
		Accessory a = new Accessory();
		a.setPrice(5f);
		AccessoryDAO aDao = DAOFactory.getAccessoryDAO();
		Accessory b = aDao.add(a);
		
		Customer testCust = new Customer();
		CustomerDAO cDao = DAOFactory.getCustomerDAO();
		testCust = cDao.add(testCust);
		
		aDao.purchase(b, testCust.getID(), 2);
		testCust = cDao.getById(testCust.getID());
		assertEquals(testCust.getBalance(), 10f);
		cDao.delete(testCust);
		aDao.delete(b);
		
		
	}
	
}
