package spencer.revature.services;

import java.util.Set;

import spencer.revature.beans.Bicycle;
import spencer.revature.beans.BicycleStatus;
import spencer.revature.beans.Customer;
import spencer.revature.data.BicycleDAO;
import spencer.revature.data.BicycleDAOFactory;
import spencer.revature.data.CustomerDAO;
import spencer.revature.data.CustomerDAOFactory;
import spencer.revature.data.OfferDAO;
import spencer.revature.data.OfferDAOFactory;

public class BicycleServiceImpl implements BicycleService {

	private BicycleDAO bikeDao;
	private CustomerDAO customerDao;
	private OfferDAO offerDao;
	
	public BicycleServiceImpl() {
		BicycleDAOFactory bikeDaoFactory = new BicycleDAOFactory();
		bikeDao = bikeDaoFactory.getBicycleDAO();
		
		CustomerDAOFactory customerDaoFactory = new CustomerDAOFactory();
		customerDao = customerDaoFactory.getCustomerDAO();
		
		OfferDAOFactory offerDaoFactory = new OfferDAOFactory();
		offerDao = offerDaoFactory.getOfferDAO();
		
	}
	
	public Integer addBicycle(Bicycle b) {
		return bikeDao.add(b).getId();
	}

	public Bicycle getBicycleById(Integer id) {
		return bikeDao.getById(id);
	}

	public Set<Bicycle> getBicycles() {
		return bikeDao.getAll();
	}

	public Set<Bicycle> getAvailableBicycles() {
		return bikeDao.getAvailableBicycles();
	}

	public void updateBicycle(Bicycle b) {
		bikeDao.update(b);
	}

	public void assignBicycle(Customer c, Bicycle b) {
		BicycleStatus status = new BicycleStatus();
    	status.setId(2);
    	status.setStatus("Sold");
    	b.setAvailable(status);
    	b.setOwner(c);
    	updateBicycle(b);
        offerDao.rejectAll(b);
		
	}

	public void removeBicycle(Bicycle b) {
		bikeDao.delete(b);
		
	}

}
