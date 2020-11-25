package com.revature.data;

import java.util.Set;

import com.revature.beans.Payment;

public interface PaymentDAO { //does not extend generic dao due to limited functionality being needed
	
	public Payment addPayment(Payment p);
	public void deletePayment(Payment p);
	public Payment getByPaymentId(Integer id);
	public Set<Payment> getByUserId(Integer id);
	public Set<Payment> getAll();

}
