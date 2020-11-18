package dev.elliman.beans;

public class Permission {
	public final Integer LEVEL;
	public final String TYPE;
	
	public Permission(String type, Integer level) {
		LEVEL = level;
		TYPE = type;
	}
}
