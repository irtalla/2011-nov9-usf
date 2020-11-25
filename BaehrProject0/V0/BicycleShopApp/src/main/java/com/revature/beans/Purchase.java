package com.revature.beans;

public class Purchase {
	private Integer purchase_id;
	private Integer user_id;
	private Bicycle bicycle;
	private double price;
	private double outstandingBalance;
	private Integer paymentsRemaining;
	
	public Purchase() {
		this.purchase_id = 0;
		this.user_id = 0;
		this.bicycle = new Bicycle();
		this.price = 0;
		this.outstandingBalance = 0.0;
		this.paymentsRemaining =0;
	}

	public Purchase(Integer purchase_id, Integer user_id, Bicycle bicycle, double price, double outstandingBalance,
			Integer paymentsRemaining) {
		super();
		this.purchase_id = purchase_id;
		this.user_id = user_id;
		this.bicycle = bicycle;
		this.price = price;
		this.outstandingBalance = outstandingBalance;
		this.paymentsRemaining = paymentsRemaining;
	}

	public Integer getPurchase_id() {
		return purchase_id;
	}

	public void setPurchase_id(Integer purchase_id) {
		this.purchase_id = purchase_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Bicycle getBicycle() {
		return bicycle;
	}

	public void setBicycle(Bicycle bicycle) {
		this.bicycle = bicycle;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getOutstandingBalance() {
		return outstandingBalance;
	}

	public void setOutstandingBalance(double outstandingBalance) {
		this.outstandingBalance = outstandingBalance;
	}

	public Integer getPaymentsRemaining() {
		return paymentsRemaining;
	}

	public void setPaymentsRemaining(Integer paymentsRemaining) {
		this.paymentsRemaining = paymentsRemaining;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bicycle == null) ? 0 : bicycle.hashCode());
		long temp;
		temp = Double.doubleToLongBits(outstandingBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((paymentsRemaining == null) ? 0 : paymentsRemaining.hashCode());
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((purchase_id == null) ? 0 : purchase_id.hashCode());
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
		Purchase other = (Purchase) obj;
		if (bicycle == null) {
			if (other.bicycle != null)
				return false;
		} else if (!bicycle.equals(other.bicycle))
			return false;
		if (Double.doubleToLongBits(outstandingBalance) != Double.doubleToLongBits(other.outstandingBalance))
			return false;
		if (paymentsRemaining == null) {
			if (other.paymentsRemaining != null)
				return false;
		} else if (!paymentsRemaining.equals(other.paymentsRemaining))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (purchase_id == null) {
			if (other.purchase_id != null)
				return false;
		} else if (!purchase_id.equals(other.purchase_id))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Purchase [purchase_id=" + purchase_id + ", user_id=" + user_id + ", bicycle=" + bicycle + ", price="
				+ price + ", outstandingBalance=" + outstandingBalance + ", paymentsRemaining=" + paymentsRemaining
				+ "]";
	}

	

}
