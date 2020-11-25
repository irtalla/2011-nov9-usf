package services;

import data.PaymentDAO;
import data.PaymentPostgress;

public class PaymentDaoFactory {
	public PaymentDAO getPaymentDAO() {
		return new PaymentPostgress();
	}
}
