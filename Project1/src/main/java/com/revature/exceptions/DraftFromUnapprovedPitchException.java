package com.revature.exceptions;

public class DraftFromUnapprovedPitchException extends Exception{
	public DraftFromUnapprovedPitchException() {
		super("Cannot create a draft from a pitch until it is fully approved.");
	}
}
