package com.revature.controllers;

import com.revature.beans.Draft;
import com.revature.services.DraftServiceImpl;

public class DraftController extends GenericController<Draft> {

	public DraftController() {
		super(Draft.class);
	}

	@Override
	DraftServiceImpl getServ() {
		return new DraftServiceImpl();
	}

}
