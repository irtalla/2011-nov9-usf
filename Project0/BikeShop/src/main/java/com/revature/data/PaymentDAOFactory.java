package com.revature.data;

public class PaymentDAOFactory {
	
	public PaymentDAO getPaymentDAO() {
		
		return new PaymentPostgres();	
	}

}
