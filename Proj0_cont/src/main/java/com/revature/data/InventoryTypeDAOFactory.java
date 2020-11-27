package com.revature.data;

public class InventoryTypeDAOFactory {
	public InventoryTypeDAO getInventoryTypeDao() {
		return new InventoryTypePostgres();
	}
}
