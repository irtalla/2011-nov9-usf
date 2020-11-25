package com.james.beans;

public class Offer {
	private Integer id;
	private String userName;
	private String bikeName;
	private Float offer;	
	private String  offerState;	

	
	public Offer () {
		id = 1;
		offerState = "None";
	}

	//	Getters and Setters
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getBikeName() {
		return bikeName;
	}


	public void setBikeName(String bikeName) {
		this.bikeName = bikeName;
	}


	public Float getOffer() {
		return offer;
	}


	public void setOffer(Float offer) {
		this.offer = offer;
	}


	public String getOfferState() {
		return offerState;
	}


	public void setOfferState(String offerState) {
		this.offerState = offerState;
	}

	//	Hash-Code
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bikeName == null) ? 0 : bikeName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((offer == null) ? 0 : offer.hashCode());
		result = prime * result + ((offerState == null) ? 0 : offerState.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		if (bikeName == null) {
			if (other.bikeName != null)
				return false;
		} else if (!bikeName.equals(other.bikeName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (offer == null) {
			if (other.offer != null)
				return false;
		} else if (!offer.equals(other.offer))
			return false;
		if (offerState == null) {
			if (other.offerState != null)
				return false;
		} else if (!offerState.equals(other.offerState))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
		
	
}
