package com.revature.beans;

public class Offer {
	Integer id;
	Double amount;
	Bicycle b;
	Person p;
	
	public Offer() {
		id = 0;
		amount = 0.00;
		b = new Bicycle();
		p = new Person();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		return true;
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
	
	public Person getPerson() {
		return p;
	}
	public void setPerson(Person p) {
		this.p = p;
	}
	
	public Bicycle getBicycle() {
		return b;
	}
	public void setBicycle(Bicycle b) {
		this.b = b;
	}
	@Override
	public String toString() {
		return "Offer [id=" + id + ", amount=" + amount + ", b=" + b + ", p=" + p + "]";
	}

	
}
