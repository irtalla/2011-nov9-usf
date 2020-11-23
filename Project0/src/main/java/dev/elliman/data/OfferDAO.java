package dev.elliman.data;

import java.util.Set;

import dev.elliman.beans.Bike;
import dev.elliman.beans.Offer;
import dev.elliman.beans.Person;

public interface OfferDAO {
	public Integer makeOffer(Offer offer);
	
	public void update(Offer offer);
	
	public void rejectAll(Bike bike);
	
	public Set<Offer> getOffers(Person person);
	
	public Set<Offer> getActiveOffers(Person person);
	
	public Set<Offer> getOffers(Bike bike);
	
	public Set<Offer> getActiveOffers(Bike bike);
	
	public Set<Offer> getAllOffers();
	
	public Set<Offer> getAllActiveOffers();
	
	public Offer getById(Integer id);
}
