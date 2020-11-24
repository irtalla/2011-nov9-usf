package com.revature.beans;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Customer {
	private Integer ID;
	private String username;
	private String password;
	private Float balance;
	private Float weeklyPayment;
	//for accessories only
	private Map<Accessory, Integer> cart;
	private Set<Bicycle> garage;
	private String name;
	//contains offer IDs
	private Set<Offer> offers;
	private Role role;
	
	public Customer()
	{
		cart = new HashMap<Accessory, Integer>();
		offers = new HashSet<Offer>();
		garage = new HashSet<Bicycle>();
		balance = 0f;
		weeklyPayment = 0f;
		name = "Default";
		username = "user";
		password = "123";
		role = new com.revature.beans.Role();
		role.setId(0);
		role.setName("customer");
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	

	public Map<Accessory, Integer> getCart() {
		return cart;
	}

	public void setCart(Map<Accessory, Integer> cart) {
		this.cart = cart;
	}

	public Set<Bicycle> getGarage() {
		return garage;
	}

	public void setGarage(Set<Bicycle> garage) {
		this.garage = garage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	public Float getWeeklyPayment() {
		return weeklyPayment;
	}

	public void setWeeklyPayment(Float weeklyPayment) {
		this.weeklyPayment = weeklyPayment;
	}
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((cart == null) ? 0 : cart.hashCode());
		result = prime * result + ((garage == null) ? 0 : garage.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((offers == null) ? 0 : offers.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((weeklyPayment == null) ? 0 : weeklyPayment.hashCode());
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
		Customer other = (Customer) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (cart == null) {
			if (other.cart != null)
				return false;
		} else if (!cart.equals(other.cart))
			return false;
		if (garage == null) {
			if (other.garage != null)
				return false;
		} else if (!garage.equals(other.garage))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (offers == null) {
			if (other.offers != null)
				return false;
		} else if (!offers.equals(other.offers))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (weeklyPayment == null) {
			if (other.weeklyPayment != null)
				return false;
		} else if (!weeklyPayment.equals(other.weeklyPayment))
			return false;
		return true;
	}

	
	
	
	
	
	
	
}
