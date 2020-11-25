package spencer.revature.services;

import java.util.Set;

import spencer.revature.beans.Bicycle;
import spencer.revature.beans.Customer;
import spencer.revature.beans.Offer;
import spencer.revature.beans.OfferStatus;
import spencer.revature.beans.Payment;
import spencer.revature.data.BicycleDAO;
import spencer.revature.data.BicycleDAOFactory;
import spencer.revature.data.CustomerDAO;
import spencer.revature.data.CustomerDAOFactory;
import spencer.revature.data.OfferDAO;
import spencer.revature.data.OfferDAOFactory;
import spencer.revature.data.PaymentDAO;
import spencer.revature.data.PaymentDAOFactory;

public class OfferServiceImpl implements OfferService {

	private BicycleDAO bikeDao;
	private CustomerDAO customerDao;
	private OfferDAO offerDao; 
	private PaymentDAO payDao;
	
	public OfferServiceImpl() {
		BicycleDAOFactory bikeDaoFactory = new BicycleDAOFactory();
		bikeDao = bikeDaoFactory.getBicycleDAO();
		
		CustomerDAOFactory customerDaoFactory = new CustomerDAOFactory();
		customerDao = customerDaoFactory.getCustomerDAO();
		
		OfferDAOFactory offerDaoFactory = new OfferDAOFactory();
		offerDao = offerDaoFactory.getOfferDAO();
		
		PaymentDAOFactory payDaoFactory = new PaymentDAOFactory();
		payDao = payDaoFactory.getPaymentDAO();
	}
	
	@Override
	public Integer addOffer(Offer o) {
		return offerDao.add(o).getId();
	}

	@Override
	public Offer getOfferById(Integer id) {
		return offerDao.getById(id);
	}

	@Override
	public Set<Offer> getOffers() {
		return offerDao.getAll();
	}
	
	public Set<Payment> getPayments() {
		return payDao.getAll();
	}

	@Override
	public void updateOffer(Offer o) {
		offerDao.update(o);
	}

	@Override
	public void removeOffer(Offer o) {
		offerDao.delete(o);
	}

	@Override
	public double weeklyRemainder(Integer id) {
		return offerDao.remainder(id);
	}
	
	@Override
	public double getPayDue(Integer id) {
		return offerDao.getPayDue(id);
	}
	
	@Override
	public Offer createOffer(Customer c, Bicycle b) {
		// TODO Auto-generated method stub
		return null;
	}

}
