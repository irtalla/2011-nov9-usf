package dev.elliman.beans;

public class Offer {
	private Integer id;
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
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Person getPerson() {
		return person;
	}

	public Bike getBike() {
		return bike;
	}

	public Integer getPrice() {
		return price;
	}

	public String getStatus() {
		return status;
	}
}
