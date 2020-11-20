package com.revature.exceptions;

public class NonUniqueUsernameException extends Exception {
	public NonUniqueUsernameException () {
		super("That username is taken.");
	}
}
