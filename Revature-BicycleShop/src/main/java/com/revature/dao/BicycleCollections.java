package com.revature.dao;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Employee;


public class BicycleCollections implements BicycleDAO {

	private Set<Bicycle> offeredBicycles;
	
	public BicycleCollections() {
		Employee user = new Employee("gutscoTheRogue", "hisDragonslayerSword");
		Bicycle bicycle = new Bicycle("Ghiradelico", "Recumbent Bike X49", "This bike has been kept in good shape. A bike meant for comfort and also to look stylish", user, 23.98);
		offeredBicycles = new HashSet<>();
		offeredBicycles.add(bicycle);
	}
	
	public Set<Bicycle> getAllAvailableBicycles(){
		return offeredBicycles;
	}
	
	public boolean addABicycle(Bicycle b) {
		return offeredBicycles.add(b);
	}
}
