package com.revature.data;

import java.util.Set;

import com.cross.beans.Offer;

public interface OfferDAO extends GenericDAO<Offer> {
	public Set<Offer> getOffersByStatus(String status);
	public Set<Offer> getOffersByCustomerId(Integer id);
	public Set<Offer> getOffersByProductId(Integer id);
	
}
