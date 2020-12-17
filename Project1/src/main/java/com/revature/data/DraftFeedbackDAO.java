package com.revature.data;

import com.revature.beans.DraftFeedback;
import com.revature.exceptions.FeedbackAsAuthorException;
import com.revature.exceptions.OutsideGenreSpecialtyException;
import com.revature.exceptions.UnexplainedDenialException;

public interface DraftFeedbackDAO extends GenericDAO<DraftFeedback>{
	DraftFeedback addDraftFeedback(DraftFeedback df) throws 
		FeedbackAsAuthorException, 
		UnexplainedDenialException,
		OutsideGenreSpecialtyException;
	//not updatable
}
