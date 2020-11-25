package com.revature.models;

public class Bike extends Inventory {

	public Bike() {
		InventoryType it = new InventoryType();
		it.setId(2);
		it.setName("Bike");
		this.setType(it);
	}

}
