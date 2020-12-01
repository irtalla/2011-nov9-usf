package com.revature.exceptions;

@SuppressWarnings("serial")
public class NonUniqueEmailException extends Exception{

	public NonUniqueEmailException() {
		super("The requested email is taken.");
	}

}
