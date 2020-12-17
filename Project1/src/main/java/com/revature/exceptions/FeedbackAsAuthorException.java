package com.revature.exceptions;

public class FeedbackAsAuthorException extends Exception{
	public FeedbackAsAuthorException() {
		super("Authors may not give feedback on pitches or drafts.");
	}
}
