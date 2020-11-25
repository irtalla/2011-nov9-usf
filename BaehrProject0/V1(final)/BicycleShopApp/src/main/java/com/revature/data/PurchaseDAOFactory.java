package com.revature.data;


public class PurchaseDAOFactory{
	public PurchaseDAO getPurchaseDAO() {        
		return new PurchasePostgreSQL();
	}

}
