package com.revature.data;

public class PaymentDAOFactory {
	public PaymentDAO getPaymentDao() {
		return new PaymentPostgres();
	}
}
