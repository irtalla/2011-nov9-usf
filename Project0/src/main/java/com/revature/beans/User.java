package com.revature.beans;

import java.util.Set;
import java.util.HashSet;

public class User {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private Set<Bike> bikes;
	private Role role;
	
	//skeleton for a user
	public User() {
		id =0;
		firstName = "";
		lastName = "";
		username = "";
		password = "";
		bikes = new HashSet<Bike>();
		role = new Role();
	}
	
	//getters and setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	public Set<Bike> getBikes() {
		return bikes;
	}

	public void setBikes(Set<Bike> bikes) {
		this.bikes = bikes;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	

}
