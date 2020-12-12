package com.revature.exceptions;

public class NonAuthorHasPitchesException extends Exception {
	public NonAuthorHasPitchesException () {
		super("A Person whose Role is not Author has pitches. "
				+ "Editors must only have pitches indirectly, through their genre committees."
		);
	}
}
