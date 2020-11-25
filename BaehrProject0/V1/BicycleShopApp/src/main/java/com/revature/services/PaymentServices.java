package com.revature.services;

import java.util.Set;

import com.revature.beans.Payment;

public interface PaymentServices {
	
	//create
		public Payment addPayment(Payment p);
		//read
		public Set<Payment> getAll();
		
		public Set<Payment> getByUserId(Integer id);
		
		public Payment getById(Integer id);
		//update - not needed
		//public void updatePayment(Payment p);
		//delete 
		public void deletePayment(Payment p);

}
