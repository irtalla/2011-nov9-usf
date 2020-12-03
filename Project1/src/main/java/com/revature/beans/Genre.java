package com.revature.beans;

public class Genre {

	private Integer id;
	private String name;
	
	public Genre() {
		id= 0;
		name = "";
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

