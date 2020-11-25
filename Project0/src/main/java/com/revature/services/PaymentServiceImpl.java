package com.revature.services;

import java.util.Set;

import com.revature.beans.Payment;
import com.revature.data.BicycleDAO;
import com.revature.data.BicyclePostgres;
import com.revature.data.PaymentDAO;
import com.revature.data.PaymentPostgres;
import com.revature.data.PersonDAO;
import com.revature.data.PersonPostgres;
import com.revature.exceptions.NonUniqueUsernameException;

public class PaymentServiceImpl implements PaymentService {
	private PaymentDAO payDao = new PaymentPostgres();

	
	@Override
	public Integer addPayment(Payment pay) throws NonUniqueUsernameException, NullPointerException {
		return payDao.add(pay).getId();
	}

	@Override
	public Payment getPaymentById(Integer id) {
		return payDao.getById(id);
	}

	@Override
	public Set<Payment> getAllPayments() {
		return payDao.getAll();
	}
	public Set<Payment> getPaymentsByUserId(Integer id){
		return payDao.getPaymentsByUserId(id);
	}
	@Override
	public void updatePayment(Payment pay) {
		payDao.update(pay);

	}

	@Override
	public void deletePayment(Payment pay) {
		payDao.delete(pay);

	}

}
