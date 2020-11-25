package com.revature.data;

import java.util.Set;

import com.revature.beans.Bicycle;

public interface BicycleDAO extends GenericDAO<Bicycle> {
	public Set<Bicycle> getAvailableBicycles();
}
