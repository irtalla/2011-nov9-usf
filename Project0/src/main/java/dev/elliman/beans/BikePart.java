package dev.elliman.beans;

public class BikePart {
	protected Integer id;
	protected Integer price;
	protected String type;
	
	public BikePart(Integer price, String type) {
		this.price = price;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}
}
