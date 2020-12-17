package com.revature.services;

import java.util.Set;

import com.revature.beans.Genre;
import com.revature.beans.GenreCommittee;
import com.revature.data.GenreCommitteeDAO;
import com.revature.data.GenreCommitteeDAOFactory;
import com.revature.data.GenreCommitteeHibernate;

public class GenreCommitteeServiceImpl extends GenericServiceImpl<GenreCommittee> implements GenreCommitteeService{

	public GenreCommitteeServiceImpl() {
		super(new GenreCommitteeDAOFactory());
	}

	@Override
	GenreCommitteeDAO getDao() {
		return new GenreCommitteeHibernate();
	}

	@Override
	public GenreCommittee getByGenre(Genre genre) {
		return getDao().getByGenre(genre);
	}

}
