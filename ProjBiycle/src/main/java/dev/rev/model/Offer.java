package dev.rev.model;

public class Offer {
	
	private int offer_id;
	private int offer_price;
	private int offer_Bicycle_id;
	
	public Offer() {
		offer_id=0;
		offer_price=0;
	offer_Person_id=0;
	offer_Bicycle_id=0;
	}
	
	@Override
	public String toString() {
		return "Offer [offer_id=" + offer_id + ", offer_price=" + offer_price + ", offer_Bicycle_id=" + offer_Bicycle_id
				+ ", offer_Person_id=" + offer_Person_id + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + offer_Bicycle_id;
		result = prime * result + offer_Person_id;
		result = prime * result + offer_id;
		result = prime * result + offer_price;
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
		if (offer_Bicycle_id != other.offer_Bicycle_id)
			return false;
		if (offer_Person_id != other.offer_Person_id)
			return false;
		if (offer_id != other.offer_id)
			return false;
		if (offer_price != other.offer_price)
			return false;
		return true;
	}
	public int getOffer_id() {
		return offer_id;
	}
	public void setOffer_id(int offer_id) {
		this.offer_id = offer_id;
	}
	public int getOffer_price() {
		return offer_price;
	}
	public void setOffer_price(int offer_price) {
		this.offer_price = offer_price;
	}
	public int getOffer_Bicycle_id() {
		return offer_Bicycle_id;
	}
	public void setOffer_Bicycle_id(int offer_Bicycle_id) {
		this.offer_Bicycle_id = offer_Bicycle_id;
	}
	public int getOffer_Person_id() {
		return offer_Person_id;
	}
	public void setOffer_Person_id(int offer_Person_id) {
		this.offer_Person_id = offer_Person_id;
	}
	private int offer_Person_id;
	
}