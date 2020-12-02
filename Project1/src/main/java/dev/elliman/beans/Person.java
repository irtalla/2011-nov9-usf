package dev.elliman.beans;

public class Person {
	private Integer id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Double amountClaimed;
	private Role role;
	
	public Person() {
		id = null;
		username = null;
		password = null;
		firstName = null;
		lastName = null;
		amountClaimed = null;
		role = null;
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

	public Double getAmountClaimed() {
		return amountClaimed;
	}

	public void setAmountClaimed(Double amountClaimed) {
		this.amountClaimed = amountClaimed;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
