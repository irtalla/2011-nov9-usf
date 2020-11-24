package com.revature.services;

import java.util.Set;

import com.revature.beans.Payment;
import com.revature.data.DAOFactory;
import com.revature.data.PaymentDAO;

public class PaymentServiceImpl implements PaymentService {
	
	PaymentDAO dao = DAOFactory.getPaymentDAO();

	@Override
	public Integer addPayment(Payment p) {
		return dao.add(p).getID();
	}

	@Override
	public Payment getPaymentById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public Set<Payment> getPayments() {
		return dao.getAll();
	}

	@Override
	public void updatePayment(Payment p) {
		dao.update(p);

	}

	@Override
	public void removePayment(Payment p) {
		dao.delete(p);

	}

}
