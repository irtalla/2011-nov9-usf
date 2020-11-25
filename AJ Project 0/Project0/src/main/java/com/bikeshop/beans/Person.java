package com.bikeshop.beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Person {
	private Integer id;
	private String username;
	private String password;
	private List<Bike> bikes;
	private String role;
	private String first;
	private String last;
	private String email;
	
	public Person() {
		id = 0;
		username = "";
		password = "";
		bikes = new ArrayList<Bike>();
		role = "";
		first = "";
		last = "";
		email = "";
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

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<Bike> getBikes() {
		return bikes;
	}

	public void setBikes(List<Bike> bikes) {
		this.bikes = bikes;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
