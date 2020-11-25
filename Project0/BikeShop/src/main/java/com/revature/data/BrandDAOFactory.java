package com.revature.data;

public class BrandDAOFactory {
	
	public BrandDAO getBrandDAO() {
		
		return new BrandPostgres();	
	}

}
