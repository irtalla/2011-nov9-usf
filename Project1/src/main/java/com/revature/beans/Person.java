package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

public class Person {
	private Integer id;
	private String password;
	private String firstName;
	private String lastName;
	private String location;
	private Department department;
	

	private Set<Course> courses;
	private Role role;
	private Role role2;
	private float funds;
	
	public Role getRole2() {
		return role2;
	}

	public void setRole2(Role role2) {
		this.role2 = role2;
	}

	public Person() {
		id = 0;
		password = "";
		courses = new HashSet<Course>();
		role = new Role();
		role2 = new Role();
		funds = 1000f;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
	public float getFunds() {
		return funds;
	}

	public void setFunds(float funds) {
		this.funds = funds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courses == null) ? 0 : courses.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + Float.floatToIntBits(funds);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((role2 == null) ? 0 : role2.hashCode());
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
		if (courses == null) {
			if (other.courses != null)
				return false;
		} else if (!courses.equals(other.courses))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (Float.floatToIntBits(funds) != Float.floatToIntBits(other.funds))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
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
		if (role2 == null) {
			if (other.role2 != null)
				return false;
		} else if (!role2.equals(other.role2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", location=" + location + ", department=" + department + ", courses=" + courses + ", role=" + role
				+ ", role2=" + role2 + ", funds=" + funds + "]";
	}

	


	
	
}
