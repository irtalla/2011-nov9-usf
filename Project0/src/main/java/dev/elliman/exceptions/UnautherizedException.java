package dev.elliman.exceptions;

public class UnautherizedException extends Exception {
	public UnautherizedException() {
		super("User does not have high enough permissions to do this.");
	}
}
