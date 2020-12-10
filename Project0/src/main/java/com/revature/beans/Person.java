package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

public class Person {
	private Integer id;
	private String username;
	private Role role;
	private Set<Bicycle> bicycles;
	
	public Person() {
		id = 0;
		username = "";
		role = new Role();
		bicycles = new HashSet<Bicycle>();
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Bicycle> getBicycles() {
		return bicycles;
	}

	public void setBicycles(Set<Bicycle> bicycles) {
		this.bicycles = bicycles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bicycles == null) ? 0 : bicycles.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (bicycles == null) {
			if (other.bicycles != null)
				return false;
		} else if (!bicycles.equals(other.bicycles))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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

	@Override
	public String toString() {
		return "Person [id=" + id + ", username=" + username + ", role=" + role + "]";
	}

}
