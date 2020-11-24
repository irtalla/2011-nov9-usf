package com.revature.beans;

public class Offer {
	private Integer id;
	private Bike bike;
	private Person person;
	private double weeklyPayment;
	private int weeks;
	
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
}
