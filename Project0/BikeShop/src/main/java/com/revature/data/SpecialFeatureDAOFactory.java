package com.revature.data;

public class SpecialFeatureDAOFactory {
	
	public SpecialFeatureDAO getSpecialFeatureDAO() {
		
		return new SpecialFeaturePostgres();	
	}

}
