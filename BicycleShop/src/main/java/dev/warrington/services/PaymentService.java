package dev.warrington.services;

import java.util.Set;

import dev.warrington.beans.Payment;

public interface PaymentService {
	
	public Integer addPayment(Payment p);
	
	public Set<Payment> getAllPayments();
	
	public Set<Payment> getMyPayments(Integer id);
	
}