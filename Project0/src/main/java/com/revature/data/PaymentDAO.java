package com.revature.data;

import com.revature.beans.Payment;

public interface PaymentDAO extends GenericDAO<Payment>{
	public Payment add(Payment p);
}
