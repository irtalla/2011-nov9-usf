package com.revature.data;

public class BikeDAOFactory {
	
	public BicycleDAO getBikeDAO() {
		return new BicyclePostgres();
	}

}
