package com.james.services;

import com.james.beans.Bike;
import com.james.beans.Offer;
import com.james.exceptions.NonUniqueUsernameException;


public interface OfferService {
	// create
			public Integer addOffer(Offer off) throws NonUniqueUsernameException;
			// read
			public Offer getOfferById(Integer id);
			public Offer getBikeByName(String name);
			// update
			public void updateOffer(Offer off);
			// delete
			public void deleteOffer(Offer off);
}
