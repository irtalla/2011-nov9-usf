package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.models.Bike;
import com.revature.models.InventoryStatus;
import com.revature.models.InventoryType;

class BikePostgresTest {
	BikeDAOFactory bFactory = new BikeDAOFactory();
	BikeDAO bDao = bFactory.getBikeDAO();
	
	@Test
	void testAdd() {
		Bike a = new Bike();
		a.setValue(20.f);
		Bike b = new Bike();
		b.setValue(20.f);
		
		Bike c = bDao.add(a);
		assertNotEquals(b.getId(), c.getId());
		bDao.delete(c);
	}
	
	@Test
	void testGetById() {
		Bike a = new Bike();
		a.setValue(400.f);
		Bike b = bDao.add(a);
		Bike c = bDao.getById(b.getId());
		assertEquals(b, c); // b and c should be equals
		bDao.delete(b);
		
		assertNull(bDao.getById(100)); // none w/ id = 100 as of testing
	}

	@Test
	void testGetAll() {
		Set<Bike> set = bDao.getAll();
		int initSize = set.size();
		Bike a = new Bike();
		a.setValue(400.f);
		Bike b = bDao.add(a);
		set = bDao.getAll();
		assertTrue(set.contains(a));
		assertNotEquals(initSize, set.size());
		bDao.delete(b);
	}
	
	@Test
	void testGetAvailableBikes() {
		Bike a = new Bike();
		a.setValue(200.f);
		Bike b = new Bike();
		b.setValue(400.f);
		Bike c = new Bike();
		c.setValue(350.f);
		InventoryStatus is = new InventoryStatus();
		is.setId(2);
		is.setName("Unavailable");
		c.setStatus(is);
		
		a = bDao.add(a);
		b = bDao.add(b);
		c = bDao.add(c);
		
		assertTrue(bDao.getAvailableBikes().contains(a));
		assertTrue(bDao.getAvailableBikes().contains(b));
		assertFalse(bDao.getAvailableBikes().contains(c));
		assertNotEquals(bDao.getAll(), bDao.getAvailableBikes());
		
		bDao.delete(a);
		bDao.delete(b);
		bDao.delete(c);
	}
	
	@Test
	public void testUpdate() {
		Bike a = new Bike();
		a.setValue(499.f);
		Bike b = bDao.add(a);
		Bike c = new Bike();
		c.setId(b.getId());
		c.setValue(2000.f);
		c.setType(b.getType());
		c.setStatus(b.getStatus());
		bDao.update(c);
		assertNotEquals(b, bDao.getById(b.getId()));
		bDao.delete(c);;
	}
	
}
