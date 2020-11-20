package dev.elliman.beans;

public class Offer {
	private Person person;
	private Bike bike;
	private Integer price;
	private String status;
	
	public Offer(Person buyer, Bike bike, Integer offerPrice) {
		person = buyer;
		this.bike = bike;
		price = offerPrice;
		status = "Pending";
	}
	
	public void accept() {
		status = "Accepted";
	}
	
	public void reject() {
		status = "Rejected";
	}
}
