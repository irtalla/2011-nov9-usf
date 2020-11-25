package com.james.data;


import com.james.beans.Offer;
import com.james.exceptions.NonUniqueUsernameException;

public interface OfferDOA extends GenericDAO<Offer> {
	public Offer add(Offer off) throws NonUniqueUsernameException;
	public void view() throws NonUniqueUsernameException;
}
