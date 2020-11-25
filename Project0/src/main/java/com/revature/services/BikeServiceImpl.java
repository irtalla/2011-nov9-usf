package com.revature.services;

import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.data.BicycleDAO;
import com.revature.data.BikeDAOFactory;
import com.revature.data.OfferDAO;
import com.revature.data.OfferPostgres;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;
import com.revature.exceptions.NonUniqueUsernameException;

public class BikeServiceImpl implements BicycleService {
	private BicycleDAO bikeDao;
	private PersonDAO personDao;
	private OfferDAO oDao;
	
	public BikeServiceImpl() {
	BikeDAOFactory bDaoFactory = new BikeDAOFactory();
	bikeDao = bDaoFactory.getBikeDAO();
	
	PersonDAOFactory personDaoFactory = new PersonDAOFactory();
	personDao = personDaoFactory.getPersonDAO();
	
	oDao = new OfferPostgres();
	}
	
	
	
	@Override
	public Integer addBicycle(Bicycle b) throws NullPointerException, NonUniqueUsernameException {
			return bikeDao.add(b).getId();

		
	}

	@Override
	public Bicycle getBicycleById(Integer id) throws NullPointerException {
			return bikeDao.getById(id);
		
	}

	@Override
	public Set<Bicycle> getBicycles() {
		return bikeDao.getAll();
	}
	@Override
	public Set<Bicycle>getAvailableBicycles(){
		return bikeDao.getAvailableBicycles();
		
	}
	@Override
	public void updateBicycle(Bicycle b) {
		bikeDao.update(b);
	}


	@Override
	public void deleteBicycle(Bicycle b) {
		bikeDao.delete(b);
		
	}

}
