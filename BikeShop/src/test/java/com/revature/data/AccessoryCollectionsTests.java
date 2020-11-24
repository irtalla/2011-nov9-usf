package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.beans.Accessory;

public class AccessoryCollectionsTests {
	
	@BeforeAll
	public static void init()
	{
		System.out.println("Beginning Tests on AccessoryCollections");
	}
	
	@AfterAll
	public static void end()
	{
		System.out.println("Ending Tests on AccessoryCollections");
	}
	
	@Test
	public void testAdd()
	{
		Accessory toAdd = new Accessory(-1, 1, false, 10, "fruit");
		AccessoryDAO dao = DAOFactory.getAccessoryDAO();
		Accessory added = dao.add(toAdd);
		assertTrue(toAdd.equals(added));
	}
	
	@Test
	public void testGetByID()
	{
		Accessory toAdd = new Accessory(-1, 1, false, 10, "fruit");
		AccessoryDAO dao = DAOFactory.getAccessoryDAO();
		dao.add(toAdd);
		assertTrue(toAdd.equals(dao.getById(1)));
	}
	
	@Test
	public void testGetAll()
	{
		Accessory toAdd = new Accessory(-1, 1, false, 10, "fruit");
		AccessoryDAO dao = DAOFactory.getAccessoryDAO();
		dao.add(toAdd);
		Set<Accessory> data = dao.getAll();
		System.out.println(data);
	}
	
	@Test
	public void testDelete()
	{
		Accessory toAdd = new Accessory(-1, 1, false, 10, "fruit");
		AccessoryDAO dao = DAOFactory.getAccessoryDAO();
		dao.add(toAdd);
		dao.delete(toAdd);
		Set<Accessory> data = dao.getAll();
		System.out.println(data);
		
	}

}
