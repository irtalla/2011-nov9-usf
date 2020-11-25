package com.revature.exceptions;

public class NonUniqueUsernameException extends Exception {
	public NonUniqueUsernameException () {
		super("This username is already taken. :(");
	}
}
