package com.revature.services;

import com.revature.beans.PitchFeedback;
import com.revature.data.PitchFeedbackDAOFactory;
import com.revature.data.PitchFeedbackHibernate;

public class PitchFeedbackServiceImpl extends GenericServiceImpl<PitchFeedback> implements PitchFeedbackService{

	public PitchFeedbackServiceImpl() {
		super(new PitchFeedbackDAOFactory());
	}

	@Override
	PitchFeedbackHibernate getDao() {
		return new PitchFeedbackHibernate();
	}

}
