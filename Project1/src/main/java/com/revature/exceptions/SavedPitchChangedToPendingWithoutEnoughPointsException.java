package com.revature.exceptions;

public class SavedPitchChangedToPendingWithoutEnoughPointsException extends Exception {
	public SavedPitchChangedToPendingWithoutEnoughPointsException() {
		super("A saved pitch cannot be updated to pending if story points exceeded by author.");
	}
}
