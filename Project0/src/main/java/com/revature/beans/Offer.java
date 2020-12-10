package com.revature.beans;

public class Offer {
	private Integer id;
	private Bicycle bicycle;
	private Double price;
	private Person person;
	
	public Offer(Integer id, Bicycle bicycle, Person person, Double price) {
		this.id = id;
		this.bicycle = bicycle;
		this.price = price;
		this.person = person;
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
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person user) {
		this.person = user;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((bicycle == null) ? 0 : bicycle.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
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
		if (bicycle == null) {
			if (other.bicycle != null)
				return false;
		} else if (!bicycle.equals(other.bicycle))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", bicycle=" + bicycle.getId() + ", price=" + price + ", person=" + person.getUsername() + "]";
	}
	
}
