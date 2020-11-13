package dev.elliman.beans;

public class Person {
	private Integer id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;//scary plain text passwords
	private String role;//employee/customer/manager/etc...
	
	public Person(String firstName, String lastName, String username, String password, String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.role = role;
	}
}
