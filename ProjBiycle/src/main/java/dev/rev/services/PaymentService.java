package dev.rev.services;

import java.util.Set;

import dev.rev.model.Payment;

public interface PaymentService {

	public Integer addpayment(Payment pay) throws Exception;
	public Set<Payment> getallpays();
}
