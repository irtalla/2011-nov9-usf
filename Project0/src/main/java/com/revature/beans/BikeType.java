package com.revature.beans;

public class BikeType {
	Integer id;
	String typeName;


	public BikeType() {
		id = 1;
		typeName = "biking bike";
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	@Override
	public String toString() {
		return "BikeType [id=" + id + ", typeName=" + typeName + "]";
	}
	
	
	
}
