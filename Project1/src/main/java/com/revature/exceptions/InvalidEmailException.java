package com.revature.exceptions;

@SuppressWarnings("serial")
public class InvalidEmailException extends Exception {
	public InvalidEmailException() {
		super("Invalid email format.");
	}
}
