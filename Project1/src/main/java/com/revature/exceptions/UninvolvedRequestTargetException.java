package com.revature.exceptions;

public class UninvolvedRequestTargetException extends Exception {
	public UninvolvedRequestTargetException() {
		super("The target of this request is not the author of a pitch, nor an editor that has given feedback.");
	}
}
