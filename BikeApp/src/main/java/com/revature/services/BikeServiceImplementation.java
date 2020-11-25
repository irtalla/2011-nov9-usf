package com.revature.services;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.data.BikeDAO;
import com.revature.data.BikeDAOFactory;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;

public class BikeServiceImplementation implements BikeService{
	private BikeDAO bikeDao;
	private PersonDAO personDao;
	
	public BikeServiceImplementation(){
		BikeDAOFactory bikeDaoFactory = new BikeDAOFactory();
		bikeDao = bikeDaoFactory.getBikeDAO();
		
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		personDao = personDaoFactory.getPersonDAO();
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
	public void adoptBike(Person p, Bike b) {
		Status status = new Status();
		status.setId(3);
		status.setName("Owned");
		b.setStatus(status);
		updateBike(b);
		Set<Bike> set = p.getBikes();
		set.add(b);
		p.setBikes(set);
		personDao.update(p);
		
	}

	@Override
	public void removeBike(Bike b) {
		bikeDao.delete(b);
	}
	
	@Override
	public void updateOffer(Person p) {
		personDao.update(p);	
	}

	@Override
	public void rejectOffer(Person p) {
		Offer offer = new Offer();
		offer.setName("Rejected");
		p.setOffer(offer);
		updateOffer(p);
	}

	@Override
	public Set<Offer> getOffers() {
		return bikeDao.getOffer();
	}
	
	@Override
	public void addOffer(Person p, Bike b) {
		bikeDao.getById(b.getId());
		Offer offer = new Offer();
		offer.setName(p.getUsername());
		offer.setOffer(347.99);
		p.setOffer(offer);
		updateOffer(p);
		Set<Offer> set = b.getOffers();
		set.add(p.getOffer());
		b.setOffers(set);
		bikeDao.update(b);
		
	}


	

}
