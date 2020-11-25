package services;

import java.util.Set;

import models.Payment;

public interface PaymentService {
	public Set<Payment> getAllByUserId(Integer i);
	public Integer addPayment(Payment p);
	public Set<Payment> getAll();
	public Payment getById(Integer id);
	public void updatePayment(Payment p);
	public void deletePayment(Payment p);
}
