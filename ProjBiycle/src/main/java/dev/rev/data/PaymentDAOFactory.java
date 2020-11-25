package dev.rev.data;

public class PaymentDAOFactory {
	
	public PaymentDAO getPaymentDAO() {
		return new PaymentPostgre();
	}
}
