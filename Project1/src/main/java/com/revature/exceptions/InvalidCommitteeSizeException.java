package com.revature.exceptions;

public class InvalidCommitteeSizeException extends Exception{
	public InvalidCommitteeSizeException() {
		super("Genre Committees must have no fewer than one and no more than two senior editors, and no fewer than three total members- none of whom may be general editors or authors.");
	}
}
