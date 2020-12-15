package com.revature.beans;

import java.util.HashSet;
import java.util.Set;


public class Person {
	private Integer id;
	private String username;
	private String password;
	private String department;
	private Role role;
	//	The higher the number, the higher up in the company
	private Integer supervisorID;
	private String directSupervisor;
	
	private Set<Request> requests;
	
	//	Constructor
	public Person() {
		id = 0;
		username = "";
		password = "";
		requests = new HashSet<Request>();
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getSupervisorID() {
		return supervisorID;
	}

	public void setSupervisorID(Integer supervisorID) {
		this.supervisorID = supervisorID;
	}

	public String getDirectSupervisor() {
		return directSupervisor;
	}

	public void setDirectSupervisor(String directSupervisor) {
		this.directSupervisor = directSupervisor;
	}

	public Set<Request> getRequests() {
		return requests;
	}

	public void setRequests(Set<Request> requests) {
		this.requests = requests;
	}
	
	//	Hash-Code
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((directSupervisor == null) ? 0 : directSupervisor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((requests == null) ? 0 : requests.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((supervisorID == null) ? 0 : supervisorID.hashCode());
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
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (directSupervisor == null) {
			if (other.directSupervisor != null)
				return false;
		} else if (!directSupervisor.equals(other.directSupervisor))
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
		if (requests == null) {
			if (other.requests != null)
				return false;
		} else if (!requests.equals(other.requests))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (supervisorID == null) {
			if (other.supervisorID != null)
				return false;
		} else if (!supervisorID.equals(other.supervisorID))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}		
	
}
