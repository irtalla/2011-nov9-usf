package com.revature.data;

import com.revature.beans.GenreCommittee;

public class GenreCommitteeDAOFactory implements GenericDAOFactory<GenreCommittee>{
	@Override
	public GenreCommitteeDAO getDAO() {
		return new GenreCommitteeHibernate();
	}
}
