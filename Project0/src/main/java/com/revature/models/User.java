package com.revature.models;

import java.util.HashSet;
import java.util.Set;

public class User {
	private Integer id;
	private String username;
	private String password;
	private Set<Role> roles;
	private Set<Inventory> itemsPurchased;
	private Set<Offer> offersMade;
	
	public User() {
		id = 0;
		username = "";
		password = "";
		roles = new HashSet<Role>();
		itemsPurchased = new HashSet<Inventory>();
		offersMade = new HashSet<Offer>();
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

	public Set<Inventory> getItemsPurchased() {
		return itemsPurchased;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setItemsPurchased(Set<Inventory> itemsPurchased) {
		this.itemsPurchased = itemsPurchased;
	}

	public Set<Offer> getOffersMade() {
		return offersMade;
	}

	public void setOffersMade(Set<Offer> offersMade) {
		this.offersMade = offersMade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemsPurchased == null) ? 0 : itemsPurchased.hashCode());
		result = prime * result + ((offersMade == null) ? 0 : offersMade.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemsPurchased == null) {
			if (other.itemsPurchased != null)
				return false;
		} else if (!itemsPurchased.equals(other.itemsPurchased))
			return false;
		if (offersMade == null) {
			if (other.offersMade != null)
				return false;
		} else if (!offersMade.equals(other.offersMade))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
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
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", roles=" + roles
				+ ", itemsPurchased=" + itemsPurchased + ", offersMade=" + offersMade + "]";
	}

}
