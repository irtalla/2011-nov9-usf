package com.revature.beans;

import java.util.ArrayList;
import java.util.List;


public abstract class Product {
	
	private Integer id;
	private String name;
	private Double price;; 
	private Boolean isOwned;
	private Integer owerId; 
	private List<Offer> offers = new ArrayList<Offer>(); 
	

	
	public Product() {
		
		this.id = 0;
		this.name = "";
		this.price = 0.00;
		this.isOwned = false; 
		this.owerId = 0;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public void setOwnerId(Integer owerId) {
		this.owerId = owerId;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
	
	public List<Offer> getOffers() {
		return offers;
	}


	public Boolean getIsOwned() {
		return isOwned;
	}


	public void setIsOwned(Boolean isOwned) {
		this.isOwned = isOwned;
	}


	public Integer getOwerId() {
		return owerId;
	}

	public abstract void setColor(String productColor);
	
	
	
	
	
	
	
	
	

}
