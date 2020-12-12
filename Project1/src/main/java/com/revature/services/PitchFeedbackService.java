package com.revature.services;

import java.util.Set;

import com.revature.beans.Draft;
import com.revature.beans.PitchFeedback;
import com.revature.exceptions.PitchFeedbackWithChangesByNonSeniorEditorException;

public interface PitchFeedbackService {
	//crud methods:	
	//create:
	public PitchFeedback addPitchFeedback(PitchFeedback pf) throws PitchFeedbackWithChangesByNonSeniorEditorException;
	
	// read:
	public Draft getDraftById(Integer id);
	public Set<Draft> getAll();
		
	//update:
	public PitchFeedback updatePitchFeedback(PitchFeedback pf) throws PitchFeedbackWithChangesByNonSeniorEditorException;
	
	// delete
	public void deleteDraft(Draft p);
}
