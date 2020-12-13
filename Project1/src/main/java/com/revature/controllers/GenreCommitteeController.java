package com.revature.controllers;

import com.revature.beans.GenreCommittee;
import com.revature.services.GenreCommitteeServiceImpl;

public class GenreCommitteeController extends GenericController<GenreCommittee>{

	public GenreCommitteeController() {
		super(GenreCommittee.class);
	}

	@Override
	GenreCommitteeServiceImpl getServ() {
		return new GenreCommitteeServiceImpl();
	}

}
