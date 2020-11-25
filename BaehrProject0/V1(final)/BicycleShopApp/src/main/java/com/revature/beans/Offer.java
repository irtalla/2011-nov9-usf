package com.revature.beans;

public class Offer {
	private Integer offer_id;
	private Bicycle bicycle;
	private Integer user_id;
	private double offer;
	private String status;
	
	public Offer() {
		this.offer_id = 0;
		this.bicycle = null;
		this.user_id = 0;
		this.offer = 0.0;
		this.status = "";
	}
	
	public Offer(Integer offer_id, Bicycle bicycle, Integer user_id, double offer, String status) {
		super();
		this.offer_id = offer_id;
		this.bicycle = bicycle;
		this.user_id = user_id;
		this.offer = offer;
		this.status = status;
	}
	public Integer getOffer_id() {
		return offer_id;
	}
	public void setOffer_id(Integer offer_id) {
		this.offer_id = offer_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public double getOffer() {
		return offer;
	}
	public void setOffer(double offer) {
		this.offer = offer;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setBicycle(Bicycle b) {
		this.bicycle = b;
	}
	public Bicycle getBicycle() {
		return this.bicycle;
	}
	

	@Override
	public String toString() {
		return "Offer [offer_id=" + offer_id + ", bicycle=" + bicycle + ", user_id=" + user_id + ", offer=" + offer
				+ ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bicycle == null) ? 0 : bicycle.hashCode());
		long temp;
		temp = Double.doubleToLongBits(offer);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((offer_id == null) ? 0 : offer_id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
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
		if (bicycle == null) {
			if (other.bicycle != null)
				return false;
		} else if (!bicycle.equals(other.bicycle))
			return false;
		if (Double.doubleToLongBits(offer) != Double.doubleToLongBits(other.offer))
			return false;
		if (offer_id == null) {
			if (other.offer_id != null)
				return false;
		} else if (!offer_id.equals(other.offer_id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}
	

}
