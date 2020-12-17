package com.revature.exceptions;

public class OutsideGenreSpecialtyException extends Exception{
	public OutsideGenreSpecialtyException() {
		super("Assistant and Senior Editors may not approve pitches with genres outside their specialty.");
	}
}
