package com.bikeshop.beans;

public class Offer {

	private Integer id;
	private Float amount;
	private Integer personID;
	private Integer bikeID;
	private Integer weeks;
	
	public Offer() {
		id = 0;
		personID = 0;
		bikeID = 0;
		amount = 0f;
		weeks = 0;
		
	}
	@Override
	public String toString() {
		return "Offer [id=" + id + ", amount=" + amount + ", personID=" + personID + ", bikeID=" + bikeID + ", weeks="
				+ weeks + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Integer getBikeID() {
		return bikeID;
	}

	public void setBikeID(Integer bikeID) {
		this.bikeID = bikeID;
	}

	public Integer getWeeks() {
		return weeks;
	}

	public void setWeeks(Integer weeks) {
		this.weeks = weeks;
	}

	public Integer getPersonID() {
		return personID;
	}

	public void setPersonID(Integer personID) {
		this.personID = personID;
	}

}
