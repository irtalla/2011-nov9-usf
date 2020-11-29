package dev.warrington.services;

import java.util.Set;

import dev.warrington.beans.Payment;
import dev.warrington.data.PaymentDAO;
import dev.warrington.data.PaymentDAOFactory;

public class PaymentServiceImpl implements PaymentService {
	
	private PaymentDAO paymentDao;
	
	public PaymentServiceImpl() {
		
		PaymentDAOFactory paymentDAOFactory = new PaymentDAOFactory();
		paymentDao = paymentDAOFactory.getPaymentDao();
		
	}

	@Override
	public Integer addPayment(Payment p) {
		
		return paymentDao.add(p).getId();
		
	}

	@Override
	public Set<Payment> getAllPayments() {
		
		return paymentDao.getAll();
		
	}

	@Override
	public Set<Payment> getMyPayments(Integer id) {
		
		return paymentDao.getMine(id);
		
	}

	@Override
	public void deletePayment(Payment p) {

		paymentDao.delete(p);
		
	}

	@Override
	public void makePayment(Payment p) {
		
		paymentDao.update(p);
		
	}

}