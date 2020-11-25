package com.revature.beans;

import java.util.Set;

public class Bike {

	private Integer id;
	private String model;
	private String color;
	private float price;
	private Status status;
	private Integer ownerId;
	//private Set<Offer> offers;
	
	public Bike() {
		id = 0;
		model = "";
		color = "";
		price = 0.0f;
		status = null;
		ownerId =0;
	
	
}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

//	public Set<Offer> getOffers() {
//		return offers;
//	}
//
//	public void setOffers(Set<Offer> offers) {
//		this.offers = offers;
//	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
	//	result = prime * result + ((offers == null) ? 0 : offers.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bike other = (Bike) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
//		if (offers == null) {
//			if (other.offers != null)
//				return false;
//		} else if (!offers.equals(other.offers))
//			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bike [id=" + id + ", model=" + model + ", color=" + color + ", price=" + price + ", status=" + status
				+  "]";
	}

	



	
	
}
