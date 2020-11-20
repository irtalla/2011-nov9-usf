package com.revature.beans;

public class Status {
	
	private Integer id;
	private String name;
	
	public Status() {
		id = 1;
		name = "Available";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
