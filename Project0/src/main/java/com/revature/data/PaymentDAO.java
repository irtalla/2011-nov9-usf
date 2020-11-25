package com.revature.data;

import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Payment;

public interface PaymentDAO extends GenericDAO<Payment>{
	public Set<Payment> getPaymentsByBicycle(Bicycle b);
	public Payment add(Payment p) throws Exception;
}
