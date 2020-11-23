package com.revature.beans;

public class Offer {
	
	
	private Integer productId;  
	private Integer customerId; 
	private Integer id; 
	private Double amount; 
	private Status status; 
	
	
	public Offer() {
		
		this.status = new Status(); 
		this.status.setId(3);
		this.status.setName("pending");
	}
	

	public Integer getProductId() {
		return productId;
	}



	public void setProductId(Integer productId) {
		this.productId = productId;
	}



	public Integer getCustomerId() {
		return customerId;
	}



	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return String.format(
				  "product id: %d"
				+ "customer id: %s"
				+ "amount: %f"
				+ "status: %s",
				productId, customerId, amount, status.getName()
				);
	}
}
