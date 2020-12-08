package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event_type")
public class EventType {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(name = "reimbursement_percent")
	private int reimbursementPercent;
	
	public EventType()
	{
		id = 6;
		name = "Other";
		reimbursementPercent = 30;
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
	public int getReimbursementPercent() {
		return reimbursementPercent;
	}
	public void setReimbursementPercent(int reimbursementPercent) {
		this.reimbursementPercent = reimbursementPercent;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + reimbursementPercent;
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
		EventType other = (EventType) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (reimbursementPercent != other.reimbursementPercent)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EventType [id=" + id + ", name=" + name + ", reimbursementPercent=" + reimbursementPercent + "]";
	}
	
	
}
