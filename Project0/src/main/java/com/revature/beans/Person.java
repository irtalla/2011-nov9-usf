package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

/*
 * 
 * 
 */


public class Person {
	
	private Integer id; 
	private String username; 
	private String password; 
	private Role role;
	
	private Set<Product> products = new HashSet<Product>(); 
	private Set<Offer> offers = new HashSet<Offer>(); 
	
	
	public Person() {
		this.role = new Role(); 
		this.role.setName("customer");
		this.role.setId(1);
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

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}
	
	
	
	
	
	
	
	
}
