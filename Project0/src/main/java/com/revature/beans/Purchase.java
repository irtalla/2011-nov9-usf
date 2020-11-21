package com.revature.beans;

public class Purchase {
	
	private Integer personId; 
	private Integer productId;
	
	public Purchase() {}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

}
