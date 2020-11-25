package services;

import java.util.Set;

import data.PaymentDAO;
import models.Payment;

public class PaymentServiceImpl implements PaymentService{
	private PaymentDAO paymentdao;
	
	public PaymentServiceImpl() {
		PaymentDaoFactory paymentdaofactory = new PaymentDaoFactory();
		paymentdao = paymentdaofactory.getPaymentDAO();
	}
	@Override
	public Payment getById(Integer i) {
		// TODO Auto-generated method stub
		return paymentdao.getById(i);
	}

	@Override
	public Integer addPayment(Payment p) {
		// TODO Auto-generated method stub
		try {
			return paymentdao.add(p).getPaymentId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Payment> getAll() {
		// TODO Auto-generated method stub
		return paymentdao.getAll();
	}



	@Override
	public void deletePayment(Payment p) {
		// TODO Auto-generated method stub
		paymentdao.delete(p);
	}
	@Override
	public void updatePayment(Payment p) {
			paymentdao.update(p);
	}
	@Override
	public Set<Payment> getAllByUserId(Integer i) {
		// TODO Auto-generated method stub
		return paymentdao.getAllByUserId(i);
	}

}
