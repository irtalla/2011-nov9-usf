package com.revature.beans;

import java.util.Set;

public class Person {
	private Integer id;
	private String username;
	private String password;
	private Role role;
	private Set<Bike> bikes;
	
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
	public Set<Bike> getBikes() {
		return bikes;
	}
	public void setBikes(Set<Bike> bikes) {
		this.bikes = bikes;
	}
}
