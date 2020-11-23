package com.revature.beans;

//import java.util.HashSet;

public class Bike {
	
	private Integer id;
	private String name;
	private Model model;
	private Double price;
	private Status status;
	
	
	
	public Bike() {
		id = 0;
		name = "";
		model = new Model();
		price = 0.00;
		
		
	}
	
	//setters and getters
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
	
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
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
	
	@Override
	public String toString() {
		return "Bike [id=" + id + ", name=" + name + ", price=" + price + ", model=" + model + ", status=" + status +
				 "]";
	}
	
}
