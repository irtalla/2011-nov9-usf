package com.revature.data;

import java.util.Set;

import com.revature.models.Payment;

public interface PaymentDAO extends GenericDAO<Payment> {
	public Payment add(Payment t);
	public Set<Payment> getByStatus(String status);
}
