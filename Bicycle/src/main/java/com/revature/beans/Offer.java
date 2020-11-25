package com.revature.beans;

public class Offer {
	private Integer id;
	private Integer personId;
	private Integer bikeId;
	private float price;
	private String accept;
	public Offer() {
		id = 0;
		personId =0;
		bikeId=0;
		price = 0.0f;
		accept="";
	
	
}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public Integer getBikeId() {
		return bikeId;
	}
	public void setBikeId(Integer bikeId) {
		this.bikeId = bikeId;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getAccept() {
		return accept;
	}
	public void setAccept(String s) {
		this.accept = s;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bikeId == null) ? 0 : bikeId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((personId == null) ? 0 : personId.hashCode());
		result = prime * result + Float.floatToIntBits(price);
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
		Offer other = (Offer) obj;
		if (bikeId == null) {
			if (other.bikeId != null)
				return false;
		} else if (!bikeId.equals(other.bikeId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (personId == null) {
			if (other.personId != null)
				return false;
		} else if (!personId.equals(other.personId))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Offer [id=" + id + ", personId=" + personId + ", bikeId=" + bikeId + ", price=" + price + ", accept="
				+ accept + "]";
	}

	
	
}
