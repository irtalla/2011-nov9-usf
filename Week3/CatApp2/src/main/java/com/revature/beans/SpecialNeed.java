package com.revature.beans;

import javax.persistence.Entity;
<<<<<<< HEAD
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
=======
>>>>>>> 743701e640a15798a972f3179361cdcc0c59d31e
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
<<<<<<< HEAD
@Table(name="special_need")
public class SpecialNeed {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
=======
@Table
public class SpecialNeed {
	@Id
>>>>>>> 743701e640a15798a972f3179361cdcc0c59d31e
	private Integer id;
	private String name;
	
	public SpecialNeed() {
		id = 0;
		name = "";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		SpecialNeed other = (SpecialNeed) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SpecialNeed [id=" + id + ", name=" + name + "]";
	}

}
