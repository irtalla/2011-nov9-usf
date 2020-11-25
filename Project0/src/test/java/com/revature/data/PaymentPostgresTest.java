package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.models.Payment;
import com.revature.models.PaymentStatus;

class PaymentPostgresTest {
	private PaymentDAOFactory pFactory = new PaymentDAOFactory();
	private PaymentDAO pDao = pFactory.getPaymentDao();

	@Test
	void testAdd() {
		Payment a = new Payment();
		a.setValue(20.f);
		PaymentStatus ps = new PaymentStatus();
		a.setStatus(ps);
		Payment b = new Payment();
		b.setValue(20.f);
		b.setStatus(ps);
		
		Payment c = pDao.add(a);
		
		assertNotEquals(b.getId(), c.getId()); // should not be equals
		assertTrue(pDao.getAll().contains(c)); // should be in the set
		pDao.delete(c);
	}
	
	@Test
	void testGetById() {
		Payment a = new Payment();
		a.setValue(20.f);
		PaymentStatus as = new PaymentStatus();
		as.setId(1);
		as.setName("Requested");
		a.setStatus(as);
		Payment b = pDao.add(a);
		Payment c = pDao.getById(b.getId());
		assertEquals(b, c); // b and c should be equals
		pDao.delete(b);
		
		assertNull(pDao.getById(5)); // none w/ id = 5 as of testing
		
	}
	
	@Test
	void testGetByStatus() {
		Payment a = new Payment();
		a.setValue(20.f);
		PaymentStatus aps = new PaymentStatus();
		a.setStatus(aps);
		Payment b = new Payment();
		b.setValue(40.f);
		PaymentStatus bps = new PaymentStatus();
		b.setStatus(bps);
		Payment c = new Payment();
		c.setValue(5.f);
		PaymentStatus cps = new PaymentStatus();
		cps.setId(3);
		cps.setName("Late");
		c.setStatus(cps);
		
		a = pDao.add(a);
		b = pDao.add(b);
		c = pDao.add(c);
		
		assertTrue(pDao.getByStatus("Requested").contains(a));
		assertTrue(pDao.getByStatus("Requested").contains(b));
		assertFalse(pDao.getByStatus("Requested").contains(c));
		assertFalse(pDao.getByStatus("Late").contains(a));
		assertFalse(pDao.getByStatus("Late").contains(b));
		assertTrue(pDao.getByStatus("Late").contains(c));
		assertNotEquals(pDao.getAll(), pDao.getByStatus("Requested"));
		
		pDao.delete(a);
		pDao.delete(b);
		pDao.delete(c);
	}

	@Test
	void testGetAll() {
		Set<Payment> set = pDao.getAll();
		int initSize = set.size();
		Payment a = new Payment();
		a.setValue(20.f);
		PaymentStatus ps = new PaymentStatus();
		a.setStatus(ps);
		Payment b = pDao.add(a);
		set = pDao.getAll();
		assertTrue(set.contains(a)); // the new set should contain a
		assertNotEquals(initSize, set.size());
		pDao.delete(b);
		
	}
	
	@Test
	public void testUpdate() {
		Payment a = new Payment();
		a.setValue(30.f);
		PaymentStatus ps = new PaymentStatus();
		a.setStatus(ps);
		Payment b = pDao.add(a);
		Payment c = new Payment();
		c.setId(b.getId());
		c.setValue(40.f); // difference factor
		c.setStatus(b.getStatus());
		pDao.update(c);
		assertNotEquals(b, pDao.getById(b.getId())); // the values should differ
		pDao.delete(c);
		
	}
	
	@Test
	public void testDelete() {
		Payment a = new Payment();
		a.setValue(20.f);
		PaymentStatus ps = new PaymentStatus();
		a.setStatus(ps);
		Payment b = pDao.add(a);
		assertTrue(pDao.getAll().contains(b));
		pDao.delete(b);
		assertFalse(pDao.getAll().contains(b));

	}
}
