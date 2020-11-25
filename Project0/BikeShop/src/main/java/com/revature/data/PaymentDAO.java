package com.revature.data;

import java.util.Set;

import com.revature.beans.Payment;

public interface PaymentDAO extends GenericDAO <Payment>  {
	public Payment add(Payment p);
	public Set<Payment> getAvailablePayments();

}
