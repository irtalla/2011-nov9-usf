package com.revature.data;

public class BicycleTypeDAOFactory {
	
	public BicycleTypeDAO getBicycleTypeDAO() {
		return new BicycleTypePostgreSQL();
	}
	
}
