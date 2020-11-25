package com.revature.beans;

public class Offer {
	private Integer id;
	private String user;
	private Integer bicycleId;
	private Integer price;
	private boolean complete;

	public Offer(Integer id, String userName, Integer bicycleId, Integer price, boolean complete) {
		this.id = id;
		this.user = userName;
		this.bicycleId = bicycleId;
		this.price = price;
		this.complete = complete;
	}

	public Integer getId() {
		return this.id;
	}
	
	public String getUserName() {
		return this.user;
	}
	
	public Integer getBicycleId() {
		return this.bicycleId;
	}
	
	public Integer getPrice() {
		return this.price;
	}
	
	public boolean getComplete() {
		return this.complete;
	}

	@Override
	public String toString() {
		return String.format("{ id: %s, price: %d, complete: %b }",id, price, complete);
	}
}
