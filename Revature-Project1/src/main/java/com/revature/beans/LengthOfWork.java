package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="work_length_type")
public class LengthOfWork {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="work_length_type_id")
	private int id;
	
	@Column(name="wlt_name")
	private String name;
	
	@Column(name="wlt_points")
	private int associatedPoints;
	
	public LengthOfWork() {
		id = 0;
		name = "";
		associatedPoints = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAssociatedPoints() {
		return associatedPoints;
	}

	public void setAssociatedPoints(int associatedPoints) {
		this.associatedPoints = associatedPoints;
	}
	
	@Override
	public String toString() {
		return "This work is a " + name + " and will cost you " + associatedPoints + " points.";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + associatedPoints;
		result = prime * result + id;
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
		LengthOfWork other = (LengthOfWork) obj;
		if (associatedPoints != other.associatedPoints)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
