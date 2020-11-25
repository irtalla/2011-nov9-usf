package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.models.InventoryStatus;

class InventoryStatusPostgresTest {
	private InventoryStatusDAOFactory isDaoFactory = new InventoryStatusDAOFactory();
	private InventoryStatusDAO isDao = isDaoFactory.getInventoryStatusDao();
	
	@Test
	void testAdd() { 
		InventoryStatus a = new InventoryStatus();
		a.setName("test");
		InventoryStatus b = new InventoryStatus();
		b.setName("test");
		InventoryStatus c = isDao.add(a); // the id is updated when added
		assertNotEquals(b.getId(), c.getId()); // should not be equals
		assertTrue(isDao.getAll().contains(c)); // should be in the set
		isDao.delete(c);
		
	}
	
	@Test
	void testGetById() {
		InventoryStatus a = isDao.getById(2);
		assertTrue(a.getId().equals(2));
		a = isDao.getById(3); // 3 is out of bounds as of testing 
		assertNull(a); // should return null
		
	}
	
	@Test
	void testGetAll() {
		Set<InventoryStatus> set = isDao.getAll();
		int initSize = set.size();
		InventoryStatus a = new InventoryStatus();
		a.setName("test");
		a = isDao.add(a);
		set = isDao.getAll();
		assertTrue(set.contains(a)); // the new set should contain a
		assertNotEquals(initSize, set.size()); // the new set should be larger
		isDao.delete(a);
		
	}
	
	@Test
	void testUpdate() {
		InventoryStatus a = isDao.getById(2);
		InventoryStatus b = new InventoryStatus();
		b.setId(a.getId());
		b.setName("test");
		isDao.update(b);
		InventoryStatus c = isDao.getById(a.getId());
		assertNotEquals(a.getName(), c.getName()); // the names should differ
		isDao.update(a);
		
	}
	
	@Test
	void testDelete() {
		InventoryStatus a = new InventoryStatus();
		a.setName("test");
		InventoryStatus b = isDao.add(a);
		assertTrue(isDao.getAll().contains(b));
		isDao.delete(b);
		assertFalse(isDao.getAll().contains(b));
		
	}

}
