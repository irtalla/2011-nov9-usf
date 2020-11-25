package com.revature.beans;

import java.util.HashSet;
import java.util.Set;


public class Person {
	private Integer id;
	private String username;
	private String password;
	private Set<Bike> bikes;
	private Role role;

	public Person() { 	
	}

	public Person(Person orig) {
		if(orig != null) {
			this.id = orig.id;
			this.username = orig.username;
			this.password = orig.password;
			this.bikes = new HashSet<>();
			if(orig.bikes != null) {
				this.bikes.addAll(orig.bikes);
			}
			if(orig.role != null) {
				this.role = new Role(orig.role);
			}
		}
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
