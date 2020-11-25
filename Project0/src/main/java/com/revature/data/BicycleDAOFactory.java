package com.revature.data;

public class BicycleDAOFactory {
	public BicycleDAO getBicycleDAO() {
		return new BicyclePostgres();
	}
}
