package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.models.InventoryType;

class InventoryTypePostgresTest {
	private InventoryTypeDAOFactory itDaoFactory = new InventoryTypeDAOFactory();
	private InventoryTypeDAO itDao = itDaoFactory.getInventoryTypeDao();
	
	@Test
	void testAdd() { 
		InventoryType a = new InventoryType();
		a.setName("test");
		InventoryType b = new InventoryType();
		b.setName("test");
		InventoryType c = itDao.add(a); // the id is updated when added
		assertNotEquals(b.getId(), c.getId()); // should not be equals
		assertTrue(itDao.getAll().contains(c)); // should be in the set
		itDao.delete(c);
		
	}
	
	@Test
	void testGetById() {
		InventoryType a = itDao.getById(2);
		assertTrue(a.getId().equals(2));
		a = itDao.getById(3); // 3 is out of bounds as of testing 
		assertNull(a); // should return null
	}
	
	@Test
	void testGetAll() {
		Set<InventoryType> set = itDao.getAll();
		int initSize = set.size();
		InventoryType a = new InventoryType();
		a.setName("test");
		a = itDao.add(a);
		set = itDao.getAll();
		assertTrue(set.contains(a)); // the new set should contain a
		assertNotEquals(initSize, set.size()); // the new set should be larger
		itDao.delete(a);
	}
	
	@Test
	void testUpdate() {
		InventoryType a = itDao.getById(2);
		InventoryType b = new InventoryType();
		b.setId(a.getId());
		b.setName("test");
		itDao.update(b);
		InventoryType c = itDao.getById(a.getId());
		assertNotEquals(a.getName(), c.getName()); // the names should differ
		itDao.update(a);
	}
	
	@Test
	void testDelete() {
		InventoryType a = new InventoryType();
		a.setName("test");
		InventoryType b = itDao.add(a);
		assertTrue(itDao.getAll().contains(b));
		itDao.delete(b);
		assertFalse(itDao.getAll().contains(b));
		
	}

}
