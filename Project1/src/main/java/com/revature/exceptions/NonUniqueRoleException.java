package com.revature.exceptions;

@SuppressWarnings("serial")
public class NonUniqueRoleException extends Exception{

	public NonUniqueRoleException() {
		super("A role by this name already exists.");
	}

}
