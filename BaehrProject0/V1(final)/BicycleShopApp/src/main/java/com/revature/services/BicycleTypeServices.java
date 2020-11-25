package com.revature.services;

import java.util.Set;

import com.revature.beans.BicycleType;

public interface BicycleTypeServices {
	
	public Integer addBicycleType(BicycleType bt);
	public BicycleType getById(Integer id);
	public Set<BicycleType> getAll();

}
