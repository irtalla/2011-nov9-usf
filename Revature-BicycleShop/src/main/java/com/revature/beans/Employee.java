package com.revature.beans;

public class Employee extends User{
	
	public Employee(String username, String password) {
		super(username, password, "employee");
	}
	
	@Override
	public String toString() {
		return "Employee " + getUsername() + "'s password is " + getPassword();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
	
	
	
}
