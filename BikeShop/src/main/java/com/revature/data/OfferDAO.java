package com.revature.data;

import com.revature.beans.Offer;

public interface OfferDAO extends GenericDAO<Offer> {
	public void AcceptOffer(Offer t);
	public void RejectOffer(Offer t);
}
