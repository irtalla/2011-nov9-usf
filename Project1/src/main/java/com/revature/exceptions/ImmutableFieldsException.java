package com.revature.exceptions;

public class ImmutableFieldsException extends Exception {
	public ImmutableFieldsException() {
		super("Update disallowed.");
	}
}
