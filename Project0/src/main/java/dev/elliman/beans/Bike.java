package dev.elliman.beans;

public class Bike {
	protected Integer id;
	protected String color;
	protected String model;
	
	public Bike(String model, String color) {
		this.model = model;
		this.color = color;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public String getModel() {
		return model;
	}
}
