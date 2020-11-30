package com.revature.services;

import java.util.Set;

import com.revature.beans.PersonBike;
import com.revature.beans.Bike;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.data.BikeDAO;
import com.revature.data.BikeDAOFactory;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;

import com.revature.data.PersonBikeDAO;
import com.revature.data.PersonBikeDAOFactory;

public class BikeServiceImpl implements BikeService {
	private BikeDAO bikeDao;
	private PersonDAO personDao;
	private PersonBikeDAO pbDAO;
	
	public BikeServiceImpl() {
		BikeDAOFactory bikeDaoFactory = new BikeDAOFactory();
		bikeDao = bikeDaoFactory.getBikeDAO();
		

		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		personDao = personDaoFactory.getPersonDAO();

		PersonBikeDAOFactory PersonBikeDAOFactory = new PersonBikeDAOFactory();
		pbDAO = PersonBikeDAOFactory.getPersonBikeDAO();
	}
	@Override
	public Integer addBike(Bike b) {
		return bikeDao.add(b).getId(); 
	}

	@Override
	public Bike getBikeById(Integer id) {
		return bikeDao.getById(id);
	}

	@Override
	public Set<Bike> getBikes() {
		return bikeDao.getAll();
	}

	@Override
	public Set<Bike> getAvailableBikes() {
		return bikeDao.getAvailableBikes();
	}

	@Override
	public void updateBike(Bike b) {
		bikeDao.update(b);

	}
	
	@Override
	public void purchaseBike(Person p, Bike b) {
		Status status = new Status();
		status.setId(2);
		status.setName("owned");
		b.setStatus(status);
		System.out.println("purchaseBike " + b);
		updateBike(b);
		
		Set<Bike> set = p.getBikes();
		set.add(b);
		System.out.println("purchaseBike " + p);
		p.setBikes(set);
		personDao.update(p); //reset it back
		
		// Add to person_bike table
		PersonBike pb = new PersonBike();
		pb.setPerson_id(p.getId());
		pb.setBike_id(b.getId());
		
		pbDAO.add(pb); 
		
	}

	@Override
	public void removeBike(Bike b) {
		bikeDao.delete(b);
	}

}
