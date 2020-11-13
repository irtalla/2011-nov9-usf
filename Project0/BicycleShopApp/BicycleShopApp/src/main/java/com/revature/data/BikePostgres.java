package com.revature.data;

import java.util.Set;

import com.revature.models.Bike;

public class BikePostgres implements BikeDAO {

	public Bike add(Bike t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Bike getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Bike> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Set<Bike> getBikesByOwner(Integer id) {
		return null;
	}

	public void update(Bike t) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Bike t) {
		// TODO Auto-generated method stub
		
	}

	public Bike setOwnership(Bike t, Integer id) {
		// TODO Auto-generated method stub
		t.setOwnerId(id);
		return null;
	}

}
