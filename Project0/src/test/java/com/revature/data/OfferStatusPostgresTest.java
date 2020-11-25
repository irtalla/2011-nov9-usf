package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.models.OfferStatus;

class OfferStatusPostgresTest {
	private OfferStatusDAOFactory osDaoFactory = new OfferStatusDAOFactory();
	private OfferStatusDAO osDao = osDaoFactory.getOfferStatusDao();
	
	@Test
	void testAdd() { 
		OfferStatus a = new OfferStatus();
		a.setName("test");
		OfferStatus b = new OfferStatus();
		b.setName("test");
		OfferStatus c = osDao.add(a); // the id is updated when added
		assertNotEquals(b.getId(), c.getId()); // should not be equals
		assertTrue(osDao.getAll().contains(c)); // should be in the set
		osDao.delete(c);
		
	}
	
	@Test
	void testGetById() {
		OfferStatus a = osDao.getById(2);
		assertTrue(a.getId().equals(2));
		a = osDao.getById(5); // 3 is out of bounds as of testing 
		assertNull(a); // should return null
	}
	
	@Test
	void testGetAll() {
		Set<OfferStatus> set = osDao.getAll();
		int initSize = set.size();
		OfferStatus a = new OfferStatus();
		a.setName("test");
		a = osDao.add(a);
		set = osDao.getAll();
		assertTrue(set.contains(a)); // the new set should contain a
		assertNotEquals(initSize, set.size()); // the new set should be larger
		osDao.delete(a);
	}
	
	@Test
	void testUpdate() {
		OfferStatus a = osDao.getById(4);
		OfferStatus b = new OfferStatus();
		b.setId(a.getId());
		b.setName("test");
		osDao.update(b);
		OfferStatus c = osDao.getById(a.getId());
		assertNotEquals(a.getName(), c.getName()); // the names should differ
		osDao.update(a);
	}
	
	@Test
	void testDelete() {
		OfferStatus a = new OfferStatus();
		a.setName("test");
		OfferStatus b = osDao.add(a);
		assertTrue(osDao.getAll().contains(b));
		osDao.delete(b);
		assertFalse(osDao.getAll().contains(b));
		
	}

}
