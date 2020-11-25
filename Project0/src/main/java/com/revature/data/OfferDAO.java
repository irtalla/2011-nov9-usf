package com.revature.data;

import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.beans.Person;

public interface OfferDAO extends GenericDAO<Offer>{
	public Offer add(Offer o);
	public Set<Offer> getOffersByBicycle(Bicycle b);
	public Set<Offer> getPendingBicycleOffers(Bicycle b);
	public Set<Offer> getOffersByPerson(Person p);
	public void updateOwner(Offer o, Person p);
}
