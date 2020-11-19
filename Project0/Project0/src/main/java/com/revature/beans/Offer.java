package com.revature.beans;

public class Offer {
	
	
	private Integer associatedProductId;  
	private Integer associatedUserId; 
	private Integer id; 
	private Double offerPrice; 
	
	public Offer() {
		
	}

	public Integer getAssociatedProductId() {
		return associatedProductId;
	}

	public void setAssociatedProductId(Integer associatedProductId) {
		this.associatedProductId = associatedProductId;
	}

	public Integer getAssociatedUserId() {
		return associatedUserId;
	}

	public void setAssociatedUserId(Integer associatedUserId) {
		this.associatedUserId = associatedUserId;
	}

	public Integer getOId() {
		return this.id;
	}

	public void setOfferId(Integer offerId) {
		this.id = offerId;
	}

	public Double getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(Double offerPrice) {
		this.offerPrice = offerPrice;
	}
	
	

}
