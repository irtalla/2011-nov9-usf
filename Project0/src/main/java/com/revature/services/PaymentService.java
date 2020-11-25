package com.revature.services;

import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Payment;
import com.revature.beans.Person;

public interface PaymentService {
	// Create
	public Integer addPayment(Payment p) throws Exception;
	
	// Read
	public Payment getPaymentById(Integer id);
	public Set<Payment> getPaymentsOnBicycle(Bicycle b);
	
	// Update
	public void updatePayment(Payment p);
	
	// Delete
	public void deletePayment(Payment p);
}
