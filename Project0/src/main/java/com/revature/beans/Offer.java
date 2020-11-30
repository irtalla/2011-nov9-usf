package com.revature.beans;

public class Offer {
	private Integer id;
	private Bike bike;
	private Person person;
	private double weeklyPayment;
	private int weeks;
	private Status status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Bike getBike() {
		return bike;
	}
	public void setBike(Bike bike) {
		this.bike = bike;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public double getWeeklyPayment() {
		return weeklyPayment;
	}
	
	public void setWeeklyPayment(double amt) {
		this.weeklyPayment = amt;
	}
	
	public int getWeeks() {
		return weeks;
	}
	
	public void setWeeks(int weeks) {
		this.weeks = weeks;
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		String rep = "Offer (with id " + this.id + ") by person with id " + this.person.getId() 
			+ " and username " + this.person.getUsername() + " to purchase bike"
			+ " with id " + this.bike.getId() + ", brand " + this.bike.getBrand() 
			+ ", model " + this.bike.getModel() + ", and color " + this.bike.getColor()
			+ " for " + this.weeks + " weekly payments of $" + this.weeklyPayment;
		return rep;
	}
}
