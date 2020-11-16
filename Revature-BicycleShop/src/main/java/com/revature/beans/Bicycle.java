package com.revature.beans;

public class Bicycle {
	private String bikeModel;
	private String bikeType;
	private String description;
	private Employee seller;
	private double price;
	private String status;
	
	public Bicycle(String model, String type, String description, Employee seller, double price) {
		bikeModel = model;
		bikeType = type;
		this.description = description;
		this.seller = seller;
		this.price = price;
		status = "available";
	}

	public String getBikeModel() {
		return bikeModel;
	}

	public void setBikeModel(String bikeModel) {
		this.bikeModel = bikeModel;
	}

	public String getBikeType() {
		return bikeType;
	}

	public void setBikeType(String bikeType) {
		this.bikeType = bikeType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(Employee seller) {
		this.seller = seller;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return bikeModel + " " + bikeType + ": " + description + "\nStarting price:" + price + "\nOffered by: " + seller.getUsername();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bikeModel == null) ? 0 : bikeModel.hashCode());
		result = prime * result + ((bikeType == null) ? 0 : bikeType.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((seller == null) ? 0 : seller.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bicycle other = (Bicycle) obj;
		if (bikeModel == null) {
			if (other.bikeModel != null)
				return false;
		} else if (!bikeModel.equals(other.bikeModel))
			return false;
		if (bikeType == null) {
			if (other.bikeType != null)
				return false;
		} else if (!bikeType.equals(other.bikeType))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (seller == null) {
			if (other.seller != null)
				return false;
		} else if (!seller.equals(other.seller))
			return false;
		return true;
	}
	
	
}
