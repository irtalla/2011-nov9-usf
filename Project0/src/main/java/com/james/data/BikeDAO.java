package com.james.data;

import com.james.beans.Bike;
import com.james.exceptions.NonUniqueUsernameException;

public interface BikeDAO extends GenericDAO<Bike> {
	public Bike add(Bike b) throws NonUniqueUsernameException;
	public void view() throws NonUniqueUsernameException;
	
}
