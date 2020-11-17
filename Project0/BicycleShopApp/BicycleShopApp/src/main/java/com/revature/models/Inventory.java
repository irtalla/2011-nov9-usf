package com.revature.models;

public abstract class Inventory {
	Integer productId;
	Integer ownerId;
	String productType;
	
	public Inventory() {
		// TODO Auto-generated constructor stub
		productId = -1;
		ownerId = -1;
		productType = "Inventory";
	}

	public Inventory(Integer productId, Integer ownerId) {
		super();
		this.productId = productId;
		this.ownerId = ownerId;
		this.productType = "Inventory";
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getProductType() {
		return productType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
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
		Inventory other = (Inventory) obj;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Inventory [productId=" + productId + ", ownerId=" + ownerId + ", productType=" + productType + "]";
	}

}
