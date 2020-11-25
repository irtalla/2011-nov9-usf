package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.revature.beans.Bicycle;
import com.revature.beans.BicycleType;
import com.revature.beans.Payment;
import com.revature.beans.Purchase;

class PaymentServicesTester {

	private static PaymentServicesImpl pServ;
	
	@BeforeAll
	public static void setUp(){
		pServ =  new PaymentServicesImpl();
	}
	
	@Test
	@Order(1)
	void getByIdTest() {
		Payment p = new Payment();
		p = pServ.getById(1);
		assertTrue(p.getPaymentId() == 1);
	}
	
	@Test
	@Order(2)
	void getByUserIdTest() {
		Set<Payment> p = new HashSet<Payment>();
		p = pServ.getByUserId(6);
		assertTrue(p.size() > 0);
	}
	
	@Test
	@Order(3)
	void getAllTest() {
		Set<Payment> p = new HashSet<Payment>();
		p = pServ.getByUserId(6);
		assertTrue(p.size() > 0);
	}

	@Test
	@Order(4)
	public void addAndDeleteTest() {
		Payment p = pServ.getById(1);
		Payment p2 = new Payment();
		p2.setAmount(99);
		p2.setUserId(8);
		pServ.addPayment(p2);
		Integer id = p2.getPaymentId();
		assertTrue(id != null && id != 0); //tests add method
		
		pServ.deletePayment(p2);
		Payment p3 = pServ.getById(id);
		assertTrue(p3 == null); //test delete method
		
	}
}
