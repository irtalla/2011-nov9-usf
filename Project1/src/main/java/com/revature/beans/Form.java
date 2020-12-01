package com.revature.beans;

public class Form {
	private Integer id;
	private String name;
	
	public Form() {
		this.id = 1;
		this.name = "article";
		
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
