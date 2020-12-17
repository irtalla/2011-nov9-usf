package com.revature.data;

import java.util.Set;

import com.revature.beans.Draft;
import com.revature.beans.DraftFeedback;
import com.revature.beans.Person;
import com.revature.beans.Pitch;
import com.revature.beans.PitchFeedback;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.exceptions.FeedbackAsAuthorException;
import com.revature.exceptions.OutsideGenreSpecialtyException;
import com.revature.exceptions.PitchFeedbackWithChangesByNonSeniorEditorException;
import com.revature.exceptions.UnexplainedDenialException;

public class PitchFeedbackHibernate extends GenericHibernate<PitchFeedback> implements PitchFeedbackDAO{

	public PitchFeedbackHibernate() {
		super(PitchFeedback.class, "pitch_feedback");
	}

	public PitchFeedback addPitchFeedback(PitchFeedback pf) throws PitchFeedbackWithChangesByNonSeniorEditorException,
			UnexplainedDenialException, FeedbackAsAuthorException, OutsideGenreSpecialtyException {
		Person offerer = pf.getEditor();
		Role role = offerer.getRole();
		Pitch pitch = pf.getPitch();
		
		if(role.equals(Role.AUTHOR)) {
			throw new FeedbackAsAuthorException();
		}else if(pf.getExplanation() == null && pf.getStatus().equals(Status.DENIED)) {
			throw new UnexplainedDenialException();
		}else if(!offerer.genreIsWithinSpecialty(pitch.getGenre())) {
			throw new OutsideGenreSpecialtyException();
		}else if(!role.equals(Role.SENIOR_EDITOR) && (pf.getNewTagLine() != null || pf.getNewTentativeTitle() != null || pf.getNewTentativeCompletionDate() != null)) {
			throw new PitchFeedbackWithChangesByNonSeniorEditorException();
		}else {
			this.add(pf);
		}
		
		return pf;
	}

	@Override
	public Set<PitchFeedback> getAllEagerlyWhereOwnerIdIs(String ownerIdName, Integer ownerId) {
		// will never be eagerly retrieved
		return this.getAllLazilyWhereOwnerIdIs(ownerIdName, ownerId);
	}

	@Override
	public PitchFeedback getByIdEagerly(Integer id) {
		// never eagerly retrieved
		return this.getByIdLazily(id);
	}

	

}
