package com.revature.beans;

import java.util.HashSet;
import java.util.Set;


public class Product {
	
	private Integer id;
	private String name;
	private Double price;
	
	private Status status; 
	private Category category; 
	
	private Set<Offer> offers = new HashSet<Offer>(); 
	private Set<Feature> features = new HashSet<Feature>(); 

	
	public Product() {
		
		this.id = 0;
		this.name = "generic product";
		this.price = 0.00;
		this.status = new Status();
		this.status.setId(1);
		this.status.setName("available");
		this.category = new Category(); 
		this.getCategory().setId(1);
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


	public Set<Offer> getOffers() {
		return offers;
	}


	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}


	public Set<Feature> getFeatures() {
		return features;
	}


	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}
	
	@Override
	public String toString() {
		return String.format(
				  "id: %d\n"
				+ "name: %s\n"
				+ "price: %f\n"
				+ "status: %s\n"
				+ "category: %s\n",
				id, name, price, status.getName(), category.getName()
				);
	}
}
