package com.revature.data;

import com.revature.beans.PitchFeedback;
import com.revature.exceptions.FeedbackAsAuthorException;
import com.revature.exceptions.OutsideGenreSpecialtyException;
import com.revature.exceptions.PitchFeedbackWithChangesByNonSeniorEditorException;
import com.revature.exceptions.UnexplainedDenialException;

public interface PitchFeedbackDAO extends GenericDAO<PitchFeedback>{
	PitchFeedback addPitchFeedback(PitchFeedback pf) throws 
		PitchFeedbackWithChangesByNonSeniorEditorException, 
		UnexplainedDenialException,
		FeedbackAsAuthorException,
		OutsideGenreSpecialtyException;
	//not updatable
}
