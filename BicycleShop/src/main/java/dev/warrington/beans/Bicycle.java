package dev.warrington.beans;

public class Bicycle {

	private Integer id;
	private String manufacturer;
	private String model;
	private Double askingPrice;
	private Integer status;
	private String color;
	
	public Bicycle(String manufacturer, String model, Double askingPrice, String color) {
		
		this.manufacturer = manufacturer;
		this.model = model;
		this.askingPrice = askingPrice;
		this.color = color;
		this.status = 1;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getAskingPrice() {
		return askingPrice;
	}

	public void setAskingPrice(Double askingPrice) {
		this.askingPrice = askingPrice;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
}