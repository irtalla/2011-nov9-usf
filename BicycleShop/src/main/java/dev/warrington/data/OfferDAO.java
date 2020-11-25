package dev.warrington.data;

import java.util.Set;

import dev.warrington.beans.Offer;

public interface OfferDAO extends GenericDAO<Offer> {
	
	public Offer add(Offer o);
	
	public Set<Offer> getAll();
	
	public void deleteAllOffersByBikeId(Integer id);
	
}