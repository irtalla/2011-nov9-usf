package com.revature.beans;

public class Bicycle {
	private Integer id;
	private String name;
	private String owner;
	
	public Bicycle(Integer id, String name, String ownerName) {
		this.id = id;
		this.name = name;
		this.owner = ownerName;	}
	
	public boolean isOwned() {
		return this.owner != null;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.format("{ id: %d, name: %s, ownerId: %d }",id, name, owner);
	}
}
