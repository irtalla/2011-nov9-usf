package dev.rev.services;

import java.util.Set;

import dev.rev.data.PaymentDAO;
import dev.rev.data.PaymentDAOFactory;
import dev.rev.model.Payment;

public class PamentServiceImp implements PaymentService{

		private PaymentDAO pDAO;
		
	public PamentServiceImp() {
			PaymentDAOFactory pdf=new PaymentDAOFactory();
			pDAO=pdf.getPaymentDAO();
		}

	@Override
	public Integer addpayment(Payment pay) throws Exception {
		// TODO Auto-generated method stub
	return pDAO.add(pay).getPayment_id();
	}

	@Override
	public Set<Payment> getallpays() {
		// TODO Auto-generated method stub
		return pDAO.getAll();
	}

}
