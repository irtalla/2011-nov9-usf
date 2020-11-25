package com.bikeshop.beans;

import java.util.Set;

public class Bike {
	private Integer id;
	private String manufacturer;
	private String model;
	private String status;
	private int inventory;
	private float price;
	private int tireSize;
	private int length;
	private String description;
	private float weeklyPayment;
	private int paymentsLeft;
	private Set<Offer> offers;
	private int offerNum;
	private int ownerID;
	
	
	public Bike () {
		id = 0;
		ownerID = 1;
		manufacturer = "";
		model = "";
		status = "";
		inventory = 0;
		price = 0f;
		offers = null;
		offerNum = 0;
		weeklyPayment = 0;
		paymentsLeft = 0;
		tireSize = 0;
		length = 0;
		description = "";
	}
	public int getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}
	public int getOfferNum() {
		return offerNum;
	}
	public void setOfferNum(int offerNum) {
		this.offerNum = offerNum;
	}
	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}
	

	@Override
	public String toString() {
		return "Bike [id=" + id + ", manufacturer=" + manufacturer + ", model=" + model + ", status=" + status
				+ ", inventory=" + inventory + ", price=" + price + ", tireSize=" + tireSize + ", length=" + length
				+ ", description=" + description + ", weeklyPayment=" + weeklyPayment + ", paymentsLeft=" + paymentsLeft
				+ ", offers=" + offers + ", offerNum=" + offerNum + ", ownerID=" + ownerID + "]";
	}
	public float getWeeklyPayment() {
		return weeklyPayment;
	}

	public void setWeeklyPayment(float weeklyPayment) {
		this.weeklyPayment = weeklyPayment;
	}

	public int getPaymentsLeft() {
		return paymentsLeft;
	}

	public void setPaymentsLeft(int paymentsLeft) {
		this.paymentsLeft = paymentsLeft;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float f) {
		this.price = f;
	}

	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffer(Set<Offer> offer) {
		this.offers = offer;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public int getTireSize() {
		return tireSize;
	}

	public void setTireSize(int tireSize) {
		this.tireSize = tireSize;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
