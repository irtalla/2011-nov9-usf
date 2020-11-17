package dev.elliman.exceptions;

public class NonUniqueUsernameException extends Exception{
	public NonUniqueUsernameException() {
		super("Username is already taken");
	}
}
