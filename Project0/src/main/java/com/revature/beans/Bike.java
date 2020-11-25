package com.revature.beans;

import java.util.Set;

public class Bike {
	private Integer id;
	private String brand;
	private String model;
	private String color;
	private Status status;
	private Set<Offer> offers;
	private Person owner;
	private Offer acceptedOffer;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Set<Offer> getOffers() {
		return offers;
	}
	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}
	public Person getOwner() {
		return owner;
	}
	public void setOwner(Person owner) {
		this.owner = owner;
	}
//	public Offer getAcceptedOffer() {
//		return acceptedOffer;
//	}
//	public void setAcceptedOffer(Offer acceptedOffer) {
//		this.acceptedOffer = acceptedOffer;
//	}
}
