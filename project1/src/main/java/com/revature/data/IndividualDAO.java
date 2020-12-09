package com.revature.data;

import com.revature.beans.Individual;

public interface IndividualDAO extends GenericDAO <Individual>{
	public Individual getByUsername(String username);
}
