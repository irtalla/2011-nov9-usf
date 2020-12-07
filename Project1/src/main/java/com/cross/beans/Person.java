package com.cross.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Person {
	@Id
	private Integer id; 
	@Column(name="passwd")
	private String username; 
	private String password; 
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="role_id")
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
