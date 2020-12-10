package com.revature.models;

public enum Priority {
	NORMAL("NORMAL"),
	HIGH("HIGH");
	
	public final String label;
	
	private Priority(String label) {
		this.label = label;
	}
	
}
