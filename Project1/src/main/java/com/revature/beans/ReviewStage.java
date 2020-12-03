package com.revature.beans;

public class ReviewStage {
	
	private Integer id;
	private String name;
	
	public ReviewStage() {
		id = 0;
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
