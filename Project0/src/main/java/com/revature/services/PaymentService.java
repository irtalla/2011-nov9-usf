package com.revature.services;

import java.util.Set;

import com.revature.models.Payment;

public interface PaymentService {
	public Integer addPayment(Payment p);
	public Payment getPaymentById(Integer id);
	public Set<Payment> getPayments();
	public Set<Payment> getPaymentByStatus(String status);
	public void updateBike(Payment p);
	public void removeBike(Payment p);
}
