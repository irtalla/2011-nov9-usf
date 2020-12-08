package com.revature.exceptions;

public class GeneralEditorWithGenreSpecialtyException extends Exception{
	public GeneralEditorWithGenreSpecialtyException() {
		super("General Editors may not specialize in a genre.");
	}
}
