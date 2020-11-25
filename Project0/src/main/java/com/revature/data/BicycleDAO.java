package com.revature.data;

import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Payment;
import com.revature.beans.Person;

public interface BicycleDAO extends GenericDAO<Bicycle> {
	public Bicycle add(Bicycle c);
	public Set<Bicycle> getAvailableBicycles();
	public Set<Bicycle> getBicyclesByOwner(Person p);
}
