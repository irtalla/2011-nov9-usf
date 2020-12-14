package com.revature.controllers;

import com.revature.beans.DraftFeedback;
import com.revature.services.DraftFeedbackServiceImpl;

public class DraftFeedbackController extends GenericController<DraftFeedback> {

	public DraftFeedbackController() {
		super(DraftFeedback.class);
	}

	@Override
	DraftFeedbackServiceImpl getServ() {
		return new DraftFeedbackServiceImpl();
	}

}
