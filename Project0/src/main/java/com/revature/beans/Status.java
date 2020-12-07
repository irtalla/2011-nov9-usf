package com.revature.beans;

public class Status {
	private Integer id;
	private String state;
	
	public Status() {
		id = 1;
		state = "Available";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStatus() {
		return state;
	}
	public void setStatus(String state) {
		this.state = state;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Status [id=" + id + ", state =" + state + "]";
	}
	
	public void changeToAvaliable() {
		id = 1;
		state = "avaliable";
	}
	
	public void changeToUnavaliable() {
		id = 2;
		state = "unavaliable";
	}
}
