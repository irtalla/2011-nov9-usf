package com.revature.beans;

import java.util.ArrayList;
import java.util.List;

/*
 * 
 * 
 */


public class Person {
	
	private Integer id; 
	private String username; 
	private String password; 
	private Role role;
	
	private List<Product> ownProducts = new ArrayList<Product>(); 
	private List<Offer> ownOffers = new ArrayList<Offer>(); 
	
	
	public Person() {
		this.role = new Role(); 
		this.role.setName("customer");
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Product> getOwnProducts() {
		return ownProducts;
	}

	public void setOwnProducts(List<Product> ownProducts) {
		this.ownProducts = ownProducts;
	}

	public List<Offer> getOwnOffers() {
		return ownOffers;
	}

	public void setOwnOffers(List<Offer> ownOffers) {
		this.ownOffers = ownOffers;
	}
	
	
	
	
	
	
}
