package com.revature.data;

import java.util.Set;

import com.revature.beans.Offer;

public interface OfferDAO extends GenericDAO<Offer> {
	public Set<Offer> getOffersByStatus(String status);
}
