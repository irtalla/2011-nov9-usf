package com.revature.beans;

//import java.util.HashSet;

public class Bike {
	
	private Integer id;
	private String model;
	private Double price;
	private Status status;
	
	public Bike() {
		id = 0;
		model = "";
		price = 0.00;
		
		
	}
	
	//setters and getters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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
	
}
