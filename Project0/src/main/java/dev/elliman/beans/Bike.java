package dev.elliman.beans;

public class Bike {
	private Integer id;
	private String color;
	private String model;
	private Person owner;
	
	
	public Bike(String model, String color) {
		this.model = model;
		this.color = color;
		owner = null;
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
	
	public void setOwner(Person owner) {
		this.owner = owner;
	}
	
	public Person getOwner() {
		return owner;
	}
}
