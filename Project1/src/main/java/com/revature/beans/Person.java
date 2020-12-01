package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

import exceptions.NonExclusiveAuthorRole;

/*
 * 
 * 
 */


public class Person {
	
	private Integer id; 
	private String username; 
	private String password; 
	private Set<Role> roles = new HashSet<Role>();
	
	private Set<Pitch> pitches = new HashSet<Pitch>(); 
	private Set<Offer> offers = new HashSet<Offer>(); 
	
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) throws Exception {
		
		if ( roles.size() > 1) {
			if ( roles.removeIf ( role -> role.getName().equalsIgnoreCase("AUTHOR")) ) {
				throw new NonExclusiveAuthorRole();
			}
		}
		this.roles = roles;
	}

	public Person() {

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


	public Set<Pitch> getPitches() {
		return pitches;
	}

	public void setPitches(Set<Pitch> products) {
		this.pitches = products;
	}


	@Override
	public String toString() {
		
		String roleString = null; 
		for (Role role : roles ) {
			roleString += role.getName() + " ";
		}
		return String.format(
				  "id: %d\n"
				+ "username: %s\n"
				+ "password: *******\n"
				+ "role(s): %s\n",
				id, username, roleString
				);
	}

}
