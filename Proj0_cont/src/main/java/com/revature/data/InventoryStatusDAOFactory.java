package com.revature.data;

public class InventoryStatusDAOFactory {
	public InventoryStatusDAO getInventoryStatusDao() {
		return new InventoryStatusPostgres();
	}
}
