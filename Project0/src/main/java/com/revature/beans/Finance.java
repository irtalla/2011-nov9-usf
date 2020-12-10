package com.revature.beans;

public class Finance {
	private Integer id;
	private Bicycle bicycle;
	private Double paidAmount;
	private Double financedAmount;
	private Payment lastPayment;
	
	public Finance() {
		id = 0;
		bicycle = new Bicycle();
		paidAmount = 0.00;
		financedAmount = 0.00;
		lastPayment = null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Bicycle getBicycle() {
		return bicycle;
	}

	public void setBicycle(Bicycle bicycle) {
		this.bicycle = bicycle;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Double getFinancedAmount() {
		return financedAmount;
	}

	public void setFinancedAmount(Double financedAmount) {
		this.financedAmount = financedAmount;
	}

	public Payment getLastPayment() {
		return lastPayment;
	}

	public void setLastPayment(Payment lastPayment) {
		this.lastPayment = lastPayment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bicycle == null) ? 0 : bicycle.hashCode());
		result = prime * result + ((financedAmount == null) ? 0 : financedAmount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastPayment == null) ? 0 : lastPayment.hashCode());
		result = prime * result + ((paidAmount == null) ? 0 : paidAmount.hashCode());
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
		Finance other = (Finance) obj;
		if (bicycle == null) {
			if (other.bicycle != null)
				return false;
		} else if (!bicycle.equals(other.bicycle))
			return false;
		if (financedAmount == null) {
			if (other.financedAmount != null)
				return false;
		} else if (!financedAmount.equals(other.financedAmount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastPayment == null) {
			if (other.lastPayment != null)
				return false;
		} else if (!lastPayment.equals(other.lastPayment))
			return false;
		if (paidAmount == null) {
			if (other.paidAmount != null)
				return false;
		} else if (!paidAmount.equals(other.paidAmount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Finance [id=" + id + ", bicycle=" + bicycle + ", paidAmount=" + paidAmount + ", financedAmount="
				+ financedAmount + ", lastPayment=" + lastPayment + "]";
	}
	
	
}
