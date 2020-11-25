package com.revature.services;

import java.util.Set;

import com.revature.beans.Payment;
import com.revature.exceptions.NonUniqueUsernameException;

public interface PaymentService {
	public Integer addPayment(Payment pay) throws NonUniqueUsernameException, NullPointerException;
	public Payment getPaymentById(Integer id);
	public Set<Payment> getAllPayments();
	public Set<Payment> getPaymentsByUserId(Integer id);
	public void updatePayment(Payment pay);
	public void deletePayment(Payment pay);
}
