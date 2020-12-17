package com.revature.data;

import com.revature.beans.PitchFeedback;

public class PitchFeedbackDAOFactory implements GenericDAOFactory<PitchFeedback>{
	@Override
	public PitchFeedbackDAO getDAO() {
		return new PitchFeedbackHibernate();
	}
}
