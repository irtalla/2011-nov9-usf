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
	
//	public boolean hasAlreadyBeenApprovedByASeniorEditor(Draft draft) {
//		for(DraftFeedback feedback : draft.getFeedback()) {
//			if(feedback.getEditor().getRole() == Role.SENIOR_EDITOR) {
//				return true;
//			}
//		}
//		return false;
//	}
}
