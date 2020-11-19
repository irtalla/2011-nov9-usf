package com.revature.beans;

public class Role {
	private Integer id = null;
	private Type role = null; 
	public static enum Type{CUSTOMER, EMPLOYEE, MANAGER};
	
	public Role() {
	}

	public Type getRole() {
		return role;
	}

	public void setRole(Type role) {
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
