package com.revature.services;

import java.util.Set;

import com.revature.data.PaymentDAO;
import com.revature.data.PaymentDAOFactory;
import com.revature.models.Payment;

public class PaymentServiceImpl implements PaymentService {
	private PaymentDAO paymentDao;
	
	public PaymentServiceImpl() {
		PaymentDAOFactory paymentDaoFactory = new PaymentDAOFactory();
		paymentDao = paymentDaoFactory.getPaymentDao();
	}

	@Override
	public Integer addPayment(Payment p) {
		return paymentDao.add(p).getId();
	}

	@Override
	public Payment getPaymentById(Integer id) {
		return paymentDao.getById(id);
	}

	@Override
	public Set<Payment> getPayments() {
		return paymentDao.getAll();
	}

	@Override
	public Set<Payment> getPaymentByStatus(String status) {
		return paymentDao.getByStatus(status);
	}

	@Override
	public void updateBike(Payment p) {
		paymentDao.update(p);
	}

	@Override
	public void removeBike(Payment p) {
		paymentDao.delete(p);
	}

}
