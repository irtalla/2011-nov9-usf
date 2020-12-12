package com.revature.exceptions;

public class RequestAsAuthorException extends Exception {
	public RequestAsAuthorException() {
		super("Person with Author role is attempting to request further information regarding a pitch.");
	}
}
