package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.Bicycle;

public class BikePostgresTest {
public DAOFactory fac = DAOFactory.getDAOFactory();
	

	@Test
	public void testAdd()
	{
		Bicycle a = new Bicycle();
		BikeDAO aDao = DAOFactory.getBikeDAO();
		Bicycle b = aDao.add(a);
		Bicycle c = new Bicycle();
		assertNotEquals(c,b);
		aDao.delete(b);
	}
	
	@Test
	public void testGetByID()
	{
		Bicycle a = new Bicycle();
		BikeDAO aDao = DAOFactory.getBikeDAO();
		Bicycle b = aDao.add(a);
		Bicycle c = aDao.getById(b.getID());
		assertEquals(b,c);
		aDao.delete(b);
	}
	
	@Test
	public void testGetAll()
	{
		Bicycle a = new Bicycle();
		BikeDAO aDao = DAOFactory.getBikeDAO();
		Bicycle b = aDao.add(a);
		
		Set<Bicycle> results = aDao.getAll();
		System.out.println(b);
		System.out.println(results);
		assertTrue(results.contains(b));
		aDao.delete(b);
	}
	
	@Test
	public void testUpdate()
	{
		Bicycle a = new Bicycle();
		BikeDAO aDao = DAOFactory.getBikeDAO();
		Bicycle b = aDao.add(a);
		
		b.setBrand("new brand");
		aDao.update(b);
		
		Bicycle result = aDao.getById(b.getID());
		assertEquals(result.getBrand(),"new brand");
		aDao.delete(b);
	}
	
	
	@Test
	public void testDelete()
	{
		Bicycle a = new Bicycle();
		BikeDAO aDao = DAOFactory.getBikeDAO();
		Bicycle b = aDao.add(a);
		aDao.delete(b);
		
		Set<Bicycle> results = aDao.getAll();
		
		assertFalse(results.contains(b));
	}
}
