package com.revature.beans;

public class StoryType {

	private Integer id;
	private String name;
	private Integer worth;
	
	public StoryType() {
		id = 0;
		name = "";
		worth = 0;
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

	public Integer getWorth() {
		return worth;
	}

	public void setWorth(Integer worth) {
		this.worth = worth;
	}
	
}
