package com.james.exceptions;

public class NonUniqueUsernameException extends Exception {
	public NonUniqueUsernameException () {
		super("The requested username is take.");
	}
}
