package com.revature.beans;

public class Customer extends User{
	
	public Customer(String username, String password) {
		super(username, password, "customer");
	}
	
	@Override
	public String toString() {
		return "Customer " + getUsername() + "'s password is" + getPassword();
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
