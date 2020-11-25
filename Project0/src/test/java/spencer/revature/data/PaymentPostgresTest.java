package spencer.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import spencer.revature.beans.Bicycle;
import spencer.revature.beans.Customer;
import spencer.revature.beans.Employee;
import spencer.revature.beans.Offer;
import spencer.revature.beans.OfferStatus;
import spencer.revature.beans.Payment;

class PaymentPostgresTest {

	/*@Test
	void BasicAdd() {
		PaymentDAO payDao;
		PaymentDAOFactory payDaoFactory = new PaymentDAOFactory();
		payDao = payDaoFactory.getPaymentDAO();
		Payment p = new Payment();
		
		p.setPayment(49.87);
		Offer o = new Offer();
		o.setId(2);
		p.setOffer(o);
		payDao.add(p);
	}*/
	
	@Test
	void GetAll() {
		PaymentDAO payDao;
		PaymentDAOFactory payDaoFactory = new PaymentDAOFactory();
		payDao = payDaoFactory.getPaymentDAO();
		
		Set<Payment> pays = payDao.getAll();
		assertTrue(pays.size()>0); //check to see if anything was grabbed
	}
	@Test
	void getID() {
		PaymentDAO payDao;
		PaymentDAOFactory payDaoFactory = new PaymentDAOFactory();
		payDao = payDaoFactory.getPaymentDAO();
		Payment p = payDao.getById(3);
		
		assertTrue(p.getPayment()==49.87);
	
	}
}
