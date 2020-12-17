package com.revature.controllers;

import com.revature.beans.PitchInfoRequest;
import com.revature.services.PitchInfoRequestServiceImpl;
public class PitchInfoRequestController extends GenericController<PitchInfoRequest>{

	public PitchInfoRequestController() {
		super(PitchInfoRequest.class);
	}

	@Override
	PitchInfoRequestServiceImpl getServ() {
		return new PitchInfoRequestServiceImpl();
	}

}
