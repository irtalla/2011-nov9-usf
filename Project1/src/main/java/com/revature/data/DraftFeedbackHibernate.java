package com.revature.data;

import com.revature.beans.Draft;
import com.revature.beans.DraftFeedback;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.exceptions.FeedbackAsAuthorException;
import com.revature.exceptions.OutsideGenreSpecialtyException;
import com.revature.exceptions.UnexplainedDenialException;

public class DraftFeedbackHibernate extends GenericHibernate<DraftFeedback> implements DraftFeedbackDAO{

	public DraftFeedbackHibernate() {
		super(DraftFeedback.class);
	}

	public DraftFeedback addDraftFeedback(DraftFeedback df)
			throws FeedbackAsAuthorException, UnexplainedDenialException, OutsideGenreSpecialtyException {
		Person offerer = df.getEditor();
		Draft draft = df.getDraft();
		
		if(offerer.getRole().equals(Role.AUTHOR)) {
			throw new FeedbackAsAuthorException();
		}else if(df.getExplanation() == null && df.getStatus().equals(Status.DENIED)) {
			throw new UnexplainedDenialException();
		}else if(!offerer.genreIsWithinSpecialty(draft.getGenre())) {
			throw new OutsideGenreSpecialtyException();
		}else {
			this.add(df);
		}
		
		return df;
	}

}
