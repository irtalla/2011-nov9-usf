package com.james.beans;

import java.util.HashSet;
import java.util.Set;

public class Person {
	private Integer id;
	private String username;
	private String password;
	private Set<Bike> bikes;
	private Role role;			
	//	For the above:	Need to commit in the database!
	
	public Person() {
		id = 0;
		username = "";
		password = "";
		bikes = new HashSet<Bike>();
		role = new Role();		
	}

	//	Getters and Setters
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

	//	Hash-Code
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bikes == null) ? 0 : bikes.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Person other = (Person) obj;
		if (bikes == null) {
			if (other.bikes != null)
				return false;
		} else if (!bikes.equals(other.bikes))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return true;
	}	
	
	
}



