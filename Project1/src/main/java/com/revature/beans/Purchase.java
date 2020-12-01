package com.revature.beans;

public class Purchase {
	
	private Integer customerId; 
	private Integer productId;
	
	public Purchase() {}
	
	
	public Integer getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}


	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	@Override
	public String toString() {
		return String.format(
				  "product id: %d"
				+ "customer id: %d",
				productId, customerId
				);
	}
}
