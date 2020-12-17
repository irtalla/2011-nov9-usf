package com.revature.exceptions;

public class PitchFeedbackWithChangesByNonSeniorEditorException extends Exception {
	public PitchFeedbackWithChangesByNonSeniorEditorException() {
		super("Editor giving Pitch feedback with changes to title, completion date or tag line is not a senior editor!");	
	}
}
