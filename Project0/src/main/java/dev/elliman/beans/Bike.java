package dev.elliman.beans;

public class Bike {
	private Integer id;
	private String color;
	private String model;
	private Integer ownerId;
	
	
	public Bike(String model, String color) {
		this.model = model;
		this.color = color;
		ownerId = 1;
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
		ownerId = owner.getID();
	}
	
	public void setOwner(Integer id) {
		ownerId = id;
	}
	
	public Integer getOwner() {
		return ownerId;
	}
}
