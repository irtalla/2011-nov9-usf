package com.revature.data;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Offer;
import com.revature.beans.Person;

public interface OfferDAO extends GenericDAO<Offer>{
	public Set<Offer> getActiveOffersMadeByPerson(Person person);
	
//	public Set<Offer> getAcceptedOffersMadeByPerson(Person person);
	
	public Set<Offer> getActiveOffersForBike(Bike b);
	
//	public Offer getAcceptedOfferForBike(Bike b);

	public void acceptOfferForBike(Bike b, Offer o);
	
	public void rejectOfferForBike(Bike b, Offer o);

}
