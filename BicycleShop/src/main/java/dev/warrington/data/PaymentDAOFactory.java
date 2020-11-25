package dev.warrington.data;

public class PaymentDAOFactory {

	public PaymentDAO getPaymentDao() {
		
		return new PaymentPostgres();
		
	}
	
}