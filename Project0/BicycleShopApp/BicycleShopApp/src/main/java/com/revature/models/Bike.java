package com.revature.models;

public class Bike extends Inventory {

	public Bike() {
		productId = -1;
		ownerId = -1;
		productType = "Bike";
	}

	public Bike(Integer productId, Integer ownerId) {
		super(productId, ownerId);
		productType = "Bike";
	}

}
