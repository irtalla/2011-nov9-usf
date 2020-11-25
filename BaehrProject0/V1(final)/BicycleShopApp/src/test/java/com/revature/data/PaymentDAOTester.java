package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.revature.beans.Payment;
import com.revature.beans.Purchase;

class PaymentDAOTester {

	private static PaymentDAO paymentDao;
	
	@BeforeAll
	public static void setUp() {
		PaymentDAOFactory paymentDaoFactory = new PaymentDAOFactory();
		paymentDao = paymentDaoFactory.getPaymentDAO();
	}
	
	@Test
	@Order(1)
	void getAllTest() {
		Set<Payment> payments = new HashSet<Payment>();
		payments = paymentDao.getAll();
		assertTrue(payments.size() > 0);
	}
	
	@Test
	@Order(2)
	void getByUserIdTest() {
		Set<Payment> payments = new HashSet<Payment>();
		payments = paymentDao.getByUserId(6);
		assertTrue(payments.size() > 0);
	}
	
	@Test
	@Order(3)
	void addAndDeleteTest() {
		Payment p =  new Payment();
		p.setAmount(100.00);
		p.setUserId(8);
		//System.out.println(p);
		Payment p2 = paymentDao.addPayment(p);
		Integer id = (p2.getPaymentId());
		//System.out.println(p2);
		assertTrue(id != null && id != 0);
		
		Payment p3 = paymentDao.getByPaymentId(id);
		paymentDao.deletePayment(p3);
		assertTrue(paymentDao.getByPaymentId(id) == null);
	}

}
