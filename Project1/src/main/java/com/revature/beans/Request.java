package com.revature.beans;

import javax.persistence.*;

@Entity
@Table
public class Request {
	@Id
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "from_person_id")
	private Person fromPerson;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "to_person_id")
	private Person toPerson;
	private String description;
	
	public Request() {
		id = 0;
		fromPerson = new Person();
		toPerson = new Person();
		description = "";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Person getFromPerson() {
		return fromPerson;
	}

	public void setFromPerson(Person fromPerson) {
		this.fromPerson = fromPerson;
	}

	public Person getToPerson() {
		return toPerson;
	}

	public void setToPerson(Person toPerson) {
		this.toPerson = toPerson;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((fromPerson == null) ? 0 : fromPerson.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((toPerson == null) ? 0 : toPerson.hashCode());
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
		Request other = (Request) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (fromPerson == null) {
			if (other.fromPerson != null)
				return false;
		} else if (!fromPerson.equals(other.fromPerson))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (toPerson == null) {
			if (other.toPerson != null)
				return false;
		} else if (!toPerson.equals(other.toPerson))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", fromPerson=" + fromPerson + ", toPerson=" + toPerson + ", description="
				+ description + "]";
	}

}
