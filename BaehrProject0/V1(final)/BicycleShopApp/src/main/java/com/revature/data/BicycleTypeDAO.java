package com.revature.data;

import java.util.Set;

import com.revature.beans.BicycleType;

public interface BicycleTypeDAO { //does not extend generic DAO because only limited functionality is required
	
	public Integer add(BicycleType bt);
	public Set<BicycleType> getAll();
	public BicycleType getById(Integer id);

}
