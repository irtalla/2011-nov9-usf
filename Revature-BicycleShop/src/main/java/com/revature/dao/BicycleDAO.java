package com.revature.dao;

import java.util.Set;

import com.revature.beans.Bicycle;

public interface BicycleDAO {
	public Set<Bicycle> getAllAvailableBicycles();
	public Set<Bicycle> getAllBicycles();
	public boolean addABicycle(Bicycle b);
	public boolean removeABicycle(Bicycle b);
}
