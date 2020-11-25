package com.revature.data;

import com.revature.models.PaymentStatus;

public interface PaymentStatusDAO extends GenericDAO<PaymentStatus> {
	public PaymentStatus add(PaymentStatus t);
}
