package com.revature.beans;

public class Status {
	
	private Integer statusID;
	private String name;
	
	public Status() {
		super();
		this.statusID = 0;
		this.name = "";
	}

	public Status(Integer statusID, String name) {
		super();
		this.statusID = statusID;
		this.name = name;
	}

	public Integer getStatusID() {
		return statusID;
	}

	public void setStatusID(Integer statusID) {
		this.statusID = statusID;
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((statusID == null) ? 0 : statusID.hashCode());
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
		Status other = (Status) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (statusID == null) {
			if (other.statusID != null)
				return false;
		} else if (!statusID.equals(other.statusID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Status [statusID=" + statusID + ", name=" + name + "]";
	}
	
	
}
