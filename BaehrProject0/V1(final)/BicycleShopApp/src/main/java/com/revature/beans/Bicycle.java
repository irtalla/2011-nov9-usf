package com.revature.beans;

public class Bicycle {
	private Integer id;
	private BicycleType bicycleType;
	private Double price;
	private Integer year;
	private Integer owner_id;
	private String status;
	
	public Bicycle () {
		this.id = 0;
		this.bicycleType = null;
		this.year = 0;
		this.price = 0.0;
		this.owner_id =0;
		this.status = "";
	}

	public Bicycle(Integer id, BicycleType bicycleType, Double price, Integer year, Integer owner_id, String status) {
		super();
		this.id = id;
		this.bicycleType = bicycleType;
		this.price = price;
		this.year = year;
		this.owner_id = owner_id;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BicycleType getBicycleType() {
		return bicycleType;
	}

	public void setBicycleType(BicycleType bicycleType) {
		this.bicycleType = bicycleType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(Integer owner_id) {
		this.owner_id = owner_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bicycleType == null) ? 0 : bicycleType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((owner_id == null) ? 0 : owner_id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		Bicycle other = (Bicycle) obj;
		if (bicycleType == null) {
			if (other.bicycleType != null)
				return false;
		} else if (!bicycleType.equals(other.bicycleType))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (owner_id == null) {
			if (other.owner_id != null)
				return false;
		} else if (!owner_id.equals(other.owner_id))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bicycle [id=" + id + ", bicycleType=" + bicycleType + ", price=" + price + ", year=" + year
				+ ", owner_id=" + owner_id + ", status=" + status + "]";
	}
	
}
