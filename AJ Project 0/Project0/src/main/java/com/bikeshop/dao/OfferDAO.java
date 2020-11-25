package com.bikeshop.dao;

import java.util.List;

import com.bikeshop.beans.Bike;
import com.bikeshop.beans.Offer;
import com.bikeshop.beans.Person;

public interface OfferDAO {
	public Offer add(Offer o);
	
	public Offer getByID(Integer id);
	public boolean updateOffer(Offer o);
	public boolean delete(Integer id);
	public List<Offer> getByBike(Bike b);
	public List<Offer> getByBikeID(Integer id);
	public List<Offer> getAllOffers();
	public boolean deleteByBikeID(Integer id);
	public List<Offer> getByPersonID(Integer id);
}
