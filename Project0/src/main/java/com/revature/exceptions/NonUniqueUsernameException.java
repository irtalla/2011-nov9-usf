package com.revature.exceptions;

public class NonUniqueUsernameException extends Exception{
	public NonUniqueUsernameException() {
		super("Users must have a unique username");
	}
}
