package com.bikeshop.services;

import java.util.List;

import com.bikeshop.beans.Bike;
import com.bikeshop.beans.Offer;
import com.bikeshop.beans.Person;

public interface OfferService {
	public List<Offer> viewOffers();
	public List<Offer> userManageOffers(Person p);
	
	public boolean addOffer(Person p, Integer id);
	public boolean declineOffer(int id);
	public boolean acceptOffer(Integer id);
	public boolean updateOffer(Integer id);
	public List<Offer> getByBike(Bike b);
	public List<Offer> getByBikeID(Integer id);
	public boolean deleteByBikeID(Integer id);
	public List<Offer> getByPersonID(Integer id);


}
