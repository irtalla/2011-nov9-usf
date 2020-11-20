package com.revature.beans;

import java.util.ArrayList;
import java.util.List;


public class Product {
	
	private Integer id;
	private String name;
	private Double price;; 
	private Integer ownerId; 
	private Status status; 
	private Category category; 
	private List<Offer> offers = new ArrayList<Offer>(); 
	private List<Feature> features = new ArrayList<Feature>(); 

	
	public Product() {
		
		this.id = 0;
		this.name = "generic product";
		this.price = 0.00;
		this.ownerId = -100;
		this.status = new Status();
		this.status.setId(null);
		this.status.setName("available");
		this.category = new Category(); 
		this.category.setId(null);
		this.category.setName("unspecified");
		
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


	public Integer getOwerId() {
		return ownerId;
	}


	public void setOwnerId(Integer owerId) {
		this.ownerId = owerId;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public List<Offer> getOffers() {
		return offers;
	}


	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}


	public List<Feature> getFeatures() {
		return features;
	}


	public void setFeatures(List<Feature> features) {
		this.features = features;
	}	
}
