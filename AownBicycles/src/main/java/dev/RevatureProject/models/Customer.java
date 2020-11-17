package dev.RevatureProject.models;

import java.util.ArrayList;

public class Customer extends Person{
	
	private String payment;
	private Role role;
	private ArrayList<Bike> bikes;

	public Customer(int id, String name, String username, String password,String payment,Role role,ArrayList<Bike> bikes) {
		super(id, name, username, password);
		// TODO Auto-generated constructor stub
		this.payment=payment;
		this.bikes=bikes;
		this.role=role;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bikes == null) ? 0 : bikes.hashCode());
		result = prime * result + ((payment == null) ? 0 : payment.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (bikes == null) {
			if (other.bikes != null)
				return false;
		} else if (!bikes.equals(other.bikes))
			return false;
		if (payment == null) {
			if (other.payment != null)
				return false;
		} else if (!payment.equals(other.payment))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public ArrayList<Bike> getBikes() {
		return bikes;
	}

	public void setBikes(ArrayList<Bike> bikes) {
		this.bikes = bikes;
	}

}
