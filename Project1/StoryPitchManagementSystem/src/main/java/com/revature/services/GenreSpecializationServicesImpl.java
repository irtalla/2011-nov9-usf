package com.revature.services;

import java.util.Set;

import com.revature.beans.GenreSpecialization;
import com.revature.data.GenreSpecializationDAO;
import com.revature.data.GenreSpecializationDAOFactory;

public class GenreSpecializationServicesImpl implements GenreSpecializationServices{

	@Override
	public GenreSpecialization getByGenreSpecializationID(Integer id) {
		GenreSpecializationDAOFactory gsDaoFactory = new GenreSpecializationDAOFactory();
		GenreSpecializationDAO gsDao = gsDaoFactory.getGenreSpecializationDAO();
		return gsDao.getByGenreSpecializationID(id);
	}

	@Override
	public Set<GenreSpecialization> getAll() {
		GenreSpecializationDAOFactory gsDaoFactory = new GenreSpecializationDAOFactory();
		GenreSpecializationDAO gsDao = gsDaoFactory.getGenreSpecializationDAO();
		return gsDao.getAll();
	}

	@Override
	public Set<GenreSpecialization> getByGenredID(Integer id) {
		GenreSpecializationDAOFactory gsDaoFactory = new GenreSpecializationDAOFactory();
		GenreSpecializationDAO gsDao = gsDaoFactory.getGenreSpecializationDAO();
		return gsDao.getByGenreID(id);
	}

	@Override
	public void updateGenreSpecialization(GenreSpecialization gs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGenreSpecialization(GenreSpecialization gs) {
		// TODO Auto-generated method stub
		
	}

}
