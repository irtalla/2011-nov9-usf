package dev.elliman.beans;

public class Person {
	private Integer id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;//scary plain text passwords
	private Role role;//employee/customer/manager/etc...
	
	public Person(String firstName, String lastName, String username, String password, Role role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setID(Integer id) {
		this.id = id;
	}
	
	public Integer getID() {
		return id;
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

	public String getUsername() {
		return username;
	}
	
	
}
