package com.revature.data;

import java.util.Set;

import com.revature.beans.Payment;

public interface PaymentDAO extends GenericDAO<Payment> {
	public Set<Payment> getPaymentsByUserId(Integer id);
}
