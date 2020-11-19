package dev.elliman.beans;

public class Bike extends BikePart {
	protected String color;
	protected String model;

	public Bike(Integer price, String model, String color) {
		super(price, "Bike");
		this.model = model;
		this.color = color;
	}
}
