package com.revature.data;

import java.util.Set;

import com.revature.models.Offer;

public interface OfferDAO extends GenericDAO<Offer> {
	public Offer add(Offer t);
	public Set<Offer> getOfferByCustomer(Integer id);
	public Set<Offer> getOfferByEmployee(Integer id);
	public Set<Offer> getOfferByStatus(String status);
}
