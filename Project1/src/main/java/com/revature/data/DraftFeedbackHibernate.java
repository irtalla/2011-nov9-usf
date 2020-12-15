package com.revature.data;

import java.util.Set;

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
		super(DraftFeedback.class, "draft_feedback");
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

	@Override
	public Set<DraftFeedback> getAllEagerlyWhereOwnerIdIs(String ownerIdName, Integer ownerId) {
		// will never be eagerly retrieved
		return this.getAllLazilyWhereOwnerIdIs(ownerIdName, ownerId);
	}

	@Override
	public DraftFeedback getByIdEagerly(Integer id) {
		// will never be eagerly retrieved
		return this.getByIdLazily(id);
	}

}
