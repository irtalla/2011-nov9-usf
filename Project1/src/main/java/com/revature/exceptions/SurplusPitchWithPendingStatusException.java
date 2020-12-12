package com.revature.exceptions;

public class SurplusPitchWithPendingStatusException extends Exception{
	public SurplusPitchWithPendingStatusException() {
		super("Pitch made after points exceeded, but status of pitch is pending.");
	}
}
