package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.models.PaymentStatus;

class PaymentStatusPostgresTest {
	private PaymentStatusDAOFactory psDaoFactory = new PaymentStatusDAOFactory();
	private PaymentStatusDAO psDao = psDaoFactory.getPaymentStatusDao();
	
	@Test
	void testAdd() { 
		PaymentStatus a = new PaymentStatus();
		a.setName("test");
		PaymentStatus b = new PaymentStatus();
		b.setName("test");
		PaymentStatus c = psDao.add(a); // the id is updated when added
		assertNotEquals(b.getId(), c.getId()); // should not be equals
		assertTrue(psDao.getAll().contains(c)); // should be in the set
		psDao.delete(c);
		
	}
	
	@Test
	void testGetById() {
		PaymentStatus a = psDao.getById(2);
		assertTrue(a.getId().equals(2));
		a = psDao.getById(7); // 3 is out of bounds as of testing 
		assertNull(a); // should return null
	}
	
	@Test
	void testGetAll() {
		Set<PaymentStatus> set = psDao.getAll();
		int initSize = set.size();
		PaymentStatus a = new PaymentStatus();
		a.setName("test");
		a = psDao.add(a);
		set = psDao.getAll();
		assertTrue(set.contains(a)); // the new set should contain a
		assertNotEquals(initSize, set.size()); // the new set should be larger
		psDao.delete(a);
	}
	
	@Test
	void testUpdate() {
		PaymentStatus a = psDao.getById(6);
		PaymentStatus b = new PaymentStatus();
		b.setId(a.getId());
		b.setName("test");
		psDao.update(b);
		PaymentStatus c = psDao.getById(a.getId());
		assertNotEquals(a.getName(), c.getName()); // the names should differ
		psDao.update(a);
	}
	
	@Test
	void testDelete() {
		PaymentStatus a = new PaymentStatus();
		a.setName("test");
		PaymentStatus b = psDao.add(a);
		assertTrue(psDao.getAll().contains(b));
		psDao.delete(b);
		assertFalse(psDao.getAll().contains(b));
		
	}

}
