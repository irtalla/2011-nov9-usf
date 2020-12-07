package com.revature.data;

import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Person;

public interface BicycleDAO extends GenericDAO<Bicycle>{
	public Bicycle add(Bicycle b);
	public Set<Bicycle> getAvailableBicycles();
	public void updateOwner(Bicycle b, Person p);
	public Set<Bicycle> getOwnedBicycles(Person p);
}
