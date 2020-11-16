package com.revature.dao;

import java.util.Set;

import com.revature.beans.Bicycle;

public interface BicycleDAO {
	public Set<Bicycle> getAllAvailableBicycles();
	public boolean addABicycle(Bicycle b);
}
