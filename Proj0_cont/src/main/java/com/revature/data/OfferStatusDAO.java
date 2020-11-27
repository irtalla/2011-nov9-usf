package com.revature.data;

import com.revature.models.OfferStatus;

public interface OfferStatusDAO extends GenericDAO<OfferStatus> {
	public OfferStatus add(OfferStatus t);
}
