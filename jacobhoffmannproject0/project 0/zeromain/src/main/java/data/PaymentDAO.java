package data;

import java.util.Set;

import models.Payment;

public interface PaymentDAO extends GenericDAO<Payment> {
	public Set<Payment> getAllByUserId(Integer i);
}
