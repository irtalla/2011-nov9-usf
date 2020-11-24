package com.revature.services;

import java.util.Set;

import com.revature.beans.Payment;

public interface PaymentService {
	public Integer addPayment(Payment a);
	// "read" methods
	public Payment getPaymentById(Integer id);
	public Set<Payment> getPayments();
	// "update" methods
	public void updatePayment(Payment c);
	// "delete" methods
	public void removePayment(Payment c);
}
