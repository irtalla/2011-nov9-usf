package dev.warrington.data;

import java.util.Set;

import dev.warrington.beans.Payment;

public interface PaymentDAO extends GenericDAO<Payment>{

	public Set<Payment> getMine(Integer Id);
	
	public Payment add(Payment p);
	
}