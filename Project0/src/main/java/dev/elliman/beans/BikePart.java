package dev.elliman.beans;

public class BikePart {
	protected Integer id;
	protected Integer price;
	protected String type;
	
	public BikePart(Integer price, String type) {
		this.price = price;
		this.type = type;
	}
}
