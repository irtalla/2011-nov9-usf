package com.revature.exceptions;

public class SurplusPitchException extends Exception{
	public SurplusPitchException() {
		super("Pitch made after points exceeded, but status of pitch is pending or approved, but not completed (referring to state of draft).");
	}
}
