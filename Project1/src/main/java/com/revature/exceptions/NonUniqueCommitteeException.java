package com.revature.exceptions;

@SuppressWarnings("serial")
public class NonUniqueCommitteeException extends Exception {
	public NonUniqueCommitteeException() {
		super("Committee of this genre already exists.");
	}

}
