package dev.warrington.services;

import java.util.Set;

import dev.warrington.beans.Payment;

public interface PaymentService {
	
	public Integer addPayment(Payment p);
	
	public Set<Payment> getAllPayments();
	
	public Set<Payment> getMyPayments(Integer id);
	
	public void deletePayment(Payment p);
	
	public void makePayment(Payment p);
}