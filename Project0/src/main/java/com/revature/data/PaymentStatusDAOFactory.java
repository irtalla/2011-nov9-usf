package com.revature.data;

public class PaymentStatusDAOFactory {
	public PaymentStatusDAO getPaymentStatusDao() {
		return new PaymentStatusPostgres();
	}
}
