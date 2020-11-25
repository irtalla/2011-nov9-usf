package dev.warrington.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import dev.warrington.beans.Payment;

public class PaymentServiceTest {

	@Test
	public void testAddPayment() {
		
		PaymentService ps = new PaymentServiceImpl();
		Payment payment1 = new Payment(3, 11, 0.00, 0.00, 0);
		
		payment1.setId(ps.addPayment(payment1));
		Set<Payment> payments = ps.getAllPayments();
		
		assertTrue(payments.contains(payment1));
		
		ps.deletePayment(payment1);
		
	}
	
	@Test
	public void testGetAllPayments() {
		
		PaymentService ps = new PaymentServiceImpl();
		Payment payment1 = new Payment(3, 11, 0.00, 0.00, 0);
		
		payment1.setId(ps.addPayment(payment1));
		
		Set<Payment> payments = ps.getAllPayments();
		
		assertTrue(payments.contains(payment1));
		
		ps.deletePayment(payment1);
		
	}
	
	@Test
	public void testDeletePayment() {
		
		PaymentService ps = new PaymentServiceImpl();
		Payment payment1 = new Payment(3, 11, 0.00, 0.00, 0);
		
		payment1.setId(ps.addPayment(payment1));
		ps.deletePayment(payment1);
		
		Set<Payment> payments = ps.getAllPayments();
		
		assertFalse(payments.contains(payment1));
		
	}
	
	@Test
	public void testGetMyPayments() {
		
		PaymentService ps = new PaymentServiceImpl();
		Set<Payment> twoPayments = ps.getMyPayments(11);
		Set<Payment> noPayments = ps.getMyPayments(3);
		
		assertTrue(twoPayments.size() == 2);
		assertTrue(noPayments.size() == 0);
		
	}
	
}