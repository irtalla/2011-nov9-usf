package com.trms.beans;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table()
public class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String first;
	private String last;
	private String email;
	private String username;
	@Column(name="passwd")
	private String password;
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="manager_id")
//	private Person manager;
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="dHead_id")
//	private Person dHead;
	
	
	@Column(name="manager_id")
	private Integer managerID;
	
	@Column(name="dHead_id")
	private Integer dHeadID;	
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="role_id")
	private Role role;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="department_id")
	private Department department;
	
	public Person() {
		id=0;
		username = "";
		password = "";
		first = "";
		last = "";
		email = "";
//		manager = new Person();
//		dHead = new Person();
		managerID = 0;
		dHeadID = 0;
		role = new Role();
		department = new Department();
		
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

//	public Person getManager() {
//		return manager;
//	}
//
//	public void setManager(Person manager) {
//		this.manager = manager;
//	}
//
//	public Person getdHead() {
//		return dHead;
//	}
//
//	public void setdHead(Person dHead) {
//		this.dHead = dHead;
//	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getManagerID() {
		return managerID;
	}

	public void setManagerID(Integer managerID) {
		this.managerID = managerID;
	}

	public Integer getdHeadID() {
		return dHeadID;
	}

	public void setdHeadID(Integer dHeadID) {
		this.dHeadID = dHeadID;
	}
}
