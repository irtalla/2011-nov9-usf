package dev.elliman.beans;

public class Item {
	protected Integer id;
	protected Integer price;
	protected String type;
	
	public Item(Integer price, String type) {
		this.price = price;
		this.type = type;
	}
}
