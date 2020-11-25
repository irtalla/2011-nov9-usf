package dev.warrington.beans;

public class Offer {
	
	private Integer id;
	private Integer bikeId;
	private Integer customerId;
	private Double amount;
	
	public Offer(Integer bikeId, Integer customerId) {
		
		this.bikeId = bikeId;
		this.customerId = customerId;
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBikeId() {
		return bikeId;
	}

	public void setBikeId(Integer bikeId) {
		this.bikeId = bikeId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}