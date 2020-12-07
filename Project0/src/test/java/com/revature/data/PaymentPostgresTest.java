package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import com.revature.beans.Payment;

public class PaymentPostgresTest {
	private PaymentPostgres paymentPostgres = new PaymentPostgres();
	private BicyclePostgres bicyclePostgres = new BicyclePostgres();

	
	@Test
	public void testGetById() {
		Payment payment = new Payment();
		payment.setId(1);
		payment.setBicycle(bicyclePostgres.getById(4));
		payment.setAmount(100.00);
		assertEquals(payment, paymentPostgres.getById(1));
	}
	
	@Test
	public void testGetAll() {
		assertEquals(1, paymentPostgres.getAll().size());
	}

	@Test
	public void testUpdate() {
		Payment payment = paymentPostgres.getById(1);
		payment.setBicycle(bicyclePostgres.getById(8));
		paymentPostgres.update(payment);
		assertEquals(payment, paymentPostgres.getById(1));
		payment.setBicycle(bicyclePostgres.getById(4));
		paymentPostgres.update(payment);
	}
	
	@Test
	@Order(1)
	public void testAdd() {
		Payment payment = new Payment();
		payment.setAmount(50.00);
		payment.setBicycle(bicyclePostgres.getById(8));
		Payment retPayment = paymentPostgres.add(payment);
		payment.setId(retPayment.getId());
		assertEquals(payment, paymentPostgres.getById(2));
	}

	@Test
	@Order(2)
	public void testDelete() {
		Payment payment = new Payment();
		payment = paymentPostgres.getById(2);
		paymentPostgres.delete(payment);
		assertEquals(1, paymentPostgres.getAll().size());
	}
	
}
