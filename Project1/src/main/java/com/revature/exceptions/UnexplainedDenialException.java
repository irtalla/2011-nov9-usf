package com.revature.exceptions;

public class UnexplainedDenialException extends Exception{
	public UnexplainedDenialException() {
		super("Must explain why a pitch or draft is denied.");
	}
}
