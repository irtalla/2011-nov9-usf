package com.revature.controllers;

import java.util.Set;

import com.revature.beans.Pitch;
import com.revature.beans.PitchFeedback;
import com.revature.services.PitchFeedbackServiceImpl;
public class PitchFeedbackController extends GenericController<PitchFeedback>{

	public PitchFeedbackController() {
		super(PitchFeedback.class);
	}

	@Override
	PitchFeedbackServiceImpl getServ() {
		return new PitchFeedbackServiceImpl();
	}

//	Set<PitchFeedback> getAllForPitch(Pitch p){
//		return getServ().getAllFor
//	}
}
