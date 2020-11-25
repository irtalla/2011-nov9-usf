package com.revature.data;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.exceptions.NonUniqueUsernameException;

public interface OfferDAO extends GenericDAO<Offer> {

	void accept(Integer id);
	

	//Offer add(Person p, Bicycle b, Offer t) throws NullPointerException, NonUniqueUsernameException;
}
