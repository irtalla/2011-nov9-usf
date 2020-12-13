package com.revature.services;

import com.revature.beans.GenreCommittee;
import com.revature.data.GenericDAO;
import com.revature.data.GenreCommitteeDAOFactory;
import com.revature.data.GenreCommitteeHibernate;

public class GenreCommitteeServiceImpl extends GenericServiceImpl<GenreCommittee> implements GenreCommitteeService{

	public GenreCommitteeServiceImpl() {
		super(new GenreCommitteeDAOFactory());
		// TODO Auto-generated constructor stub
	}

	@Override
	GenericDAO<GenreCommittee> getDao() {
		return new GenreCommitteeHibernate();
	}

}
