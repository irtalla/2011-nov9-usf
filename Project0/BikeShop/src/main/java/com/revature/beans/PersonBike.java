package com.revature.beans;

public class PersonBike {
	private Integer person_id;
	private Integer bike_id;
	
	public Integer getPerson_id() {
		return person_id;
	}
	public void setPerson_id(Integer person_id) {
		this.person_id = person_id;
	}
	public Integer getBike_id() {
		return bike_id;
	}
	public void setBike_id(Integer bike_id) {
		this.bike_id = bike_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bike_id == null) ? 0 : bike_id.hashCode());
		result = prime * result + ((person_id == null) ? 0 : person_id.hashCode());
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
		PersonBike other = (PersonBike) obj;
		if (bike_id == null) {
			if (other.bike_id != null)
				return false;
		} else if (!bike_id.equals(other.bike_id))
			return false;
		if (person_id == null) {
			if (other.person_id != null)
				return false;
		} else if (!person_id.equals(other.person_id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PersonBike [person_id=" + person_id + ", bike_id=" + bike_id + "]";
	}
	

}
