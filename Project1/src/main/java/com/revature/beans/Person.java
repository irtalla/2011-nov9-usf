package com.revature.beans;


public class Person {
	
	private Integer id; 
	private String username; 
	private String password; 
	private Role role;
	private Integer points; 

	
	public Person() {}

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


	public Integer getPoints() {
		return points;
	}


	public void setPoints(Integer points) {
		this.points = points;
	}




	@Override
	public String toString() {
		

		return String.format(
				  "id: %d\n"
				+ "username: %s\n"
				+ "password: *******\n"
				+ "role(s): %s\n",
				id, username, role.getName()
				);
	}

}
