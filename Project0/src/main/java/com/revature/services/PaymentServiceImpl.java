package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Payment;
import com.revature.beans.Person;
import com.revature.data.PaymentDAO;
import com.revature.data.PaymentDAOFactory;

public class PaymentServiceImpl implements PaymentService{
	private PaymentDAO paymentDAO;
	
	public PaymentServiceImpl() {
		PaymentDAOFactory paymentDAOFactory = new PaymentDAOFactory();
		paymentDAO = paymentDAOFactory.getPaymentDAO();
	}

	@Override
	public Integer addPayment(Payment p) throws Exception {
		return paymentDAO.add(p).getId();
	}

	@Override
	public Payment getPaymentById(Integer id) {
		return paymentDAO.getById(id);
	}

	@Override
	public Set<Payment> getPaymentsOnBicycle(Bicycle b) {
		Set<Payment> bicyclePayments = new HashSet<>();
		
		for(Payment p : paymentDAO.getAll()) {
			if(p.getBicycle().equals(b))
				bicyclePayments.add(p);
		}
		
		return bicyclePayments;
	}

	@Override
	public void updatePayment(Payment p) {
		paymentDAO.update(p);
	}

	@Override
	public void deletePayment(Payment p) {
		paymentDAO.delete(p);
	}

}
