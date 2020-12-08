package com.revature.exceptions;

public class GenreEditorWithoutGenreSpecialtyException extends Exception{
	public GenreEditorWithoutGenreSpecialtyException() {
		super("Assistant and Senior Editors must specialize in a genre.");
	}
}
