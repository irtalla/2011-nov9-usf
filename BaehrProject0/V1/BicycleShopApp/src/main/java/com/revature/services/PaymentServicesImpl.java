package com.revature.services;

import java.util.Set;

import com.revature.beans.Payment;
import com.revature.data.PaymentDAO;
import com.revature.data.PaymentDAOFactory;
import com.revature.data.PurchaseDAOFactory;

public class PaymentServicesImpl implements PaymentServices{
	
	private PaymentDAO paymentDao;
	
	public PaymentServicesImpl() {

		PaymentDAOFactory paymentDaoFactory = new PaymentDAOFactory();
		paymentDao = paymentDaoFactory.getPaymentDAO();
	}

	@Override
	public Payment addPayment(Payment p) {
		return paymentDao.addPayment(p);
	}

	@Override
	public Set<Payment> getAll() {
		return paymentDao.getAll();
	}

	@Override
	public Set<Payment> getByUserId(Integer id) {
		return paymentDao.getByUserId(id);
	}

	@Override
	public Payment getById(Integer id) {
		return paymentDao.getByPaymentId(id);
	}

	@Override
	public void deletePayment(Payment p) {
		paymentDao.deletePayment(p);
		
	}

}
