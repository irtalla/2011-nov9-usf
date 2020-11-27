package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.models.Offer;
import com.revature.models.OfferStatus;

class OfferPostgresTest {
	private OfferDAOFactory oFactory = new OfferDAOFactory();
	private OfferDAO oDao = oFactory.getOfferDao();

	@Test
	void testAdd() {
		Offer a = new Offer();
		a.setItemId(1);
		a.setValue(40.f);
		a.setEmployeeId(0);
		OfferStatus os = new OfferStatus();
		a.setStatus(os);
		Offer b = new Offer();
		b.setItemId(a.getItemId());
		b.setValue(a.getValue());
		b.setEmployeeId(a.getEmployeeId());
		b.setStatus(os);
		
		Offer c = oDao.add(a);
		
		assertNotEquals(b.getId(), c.getId());
		oDao.delete(c);
		
	}

	@Test
	void testGetById() {
		Offer a = new Offer();
		a.setItemId(1);
		a.setValue(40.f);
		a.setEmployeeId(0);
		OfferStatus os = new OfferStatus();
		a.setStatus(os);
		Offer b = oDao.add(a);
		Offer c = oDao.getById(b.getId());
		assertEquals(b,c);
		oDao.delete(b);
		
		assertNull(oDao.getById(5));
		
	}
	
	@Test
	void testgetAll() {
		Set<Offer> set = oDao.getAll();
		int initSize = set.size();
		Offer a = new Offer();
		a.setItemId(1);
		a.setValue(20.f);
		a.setEmployeeId(0);
		OfferStatus os = new OfferStatus();
		a.setStatus(os);
		Offer b = oDao.add(a);
		assertNotEquals(initSize, oDao.getAll().size());
		assertTrue(oDao.getAll().contains(b));
		oDao.delete(b);
	}
	
	@Test
	void testDelete() {
		Offer a = new Offer();
		a.setItemId(1);
		a.setValue(20.f);
		a.setEmployeeId(0);
		OfferStatus os = new OfferStatus();
		a.setStatus(os);
		Offer b = oDao.add(a);
		assertTrue(oDao.getAll().contains(b));
		oDao.delete(b);
		assertFalse(oDao.getAll().contains(b));
	}
}
