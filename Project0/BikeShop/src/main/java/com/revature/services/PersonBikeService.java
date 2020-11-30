package com.revature.services;

import com.revature.beans.Person;
import com.revature.beans.PersonBike;
import com.revature.beans.Bike;

public interface PersonBikeService {
	
	public Integer addPersonBike(Person p, Bike b);
	
	public Integer addPersonBike(Integer pid, Integer bid);
}

