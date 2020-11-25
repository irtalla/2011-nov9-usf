package com.revature.data;

public class BikeDAOFactory {
	
	public BikeDAO getBikeDAO() {
		
		return new BikePostgres();	
	}
}