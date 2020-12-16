package com.revature.services;

import java.util.Set;

import com.revature.beans.Genre;
import com.revature.beans.Pitch;
import com.revature.beans.Storytype;
import com.revature.data.GenreDAO;
import com.revature.data.GenreDAOFactory;
import com.revature.data.PitchDAO;
import com.revature.data.PitchDAOFactory;
import com.revature.data.StorytypeDAO;
import com.revature.data.StorytypeDAOFactory;

public class PitchServiceImpl implements PitchService{
	private PitchDAO pitchDao;
	private GenreDAO genreDao;
	private StorytypeDAO storytypeDao;
	
	public PitchServiceImpl() {
		PitchDAOFactory pitchDaoFactory = new PitchDAOFactory();
		pitchDao = pitchDaoFactory.getPitchDAO();
		
		GenreDAOFactory genreDaoFactory = new GenreDAOFactory();
		genreDao = genreDaoFactory.getGenreDAO();
		
		StorytypeDAOFactory typeDaoFactory = new StorytypeDAOFactory();
		storytypeDao = typeDaoFactory.getStorytypeDAO();
		
	}
	@Override
	public Integer addPitch(Pitch p) {
		return pitchDao.add(p).getId();
	}

	@Override
	public Pitch getPitchById(Integer id) {
		return pitchDao.getById(id);
	}

	@Override
	public void updatePitch(Pitch p) {
		pitchDao.update(p);
	}

	@Override
	public void deletePitch(Pitch p) {
		pitchDao.delete(p);
	}
	@Override
	public Set<Pitch> getAll() {
	    return pitchDao.getAll();
	}
	@Override
	public Set<Genre> getGenres() {
		return genreDao.getAll();
	}
	@Override
	public Set<Storytype> getTypes() {
		return storytypeDao.getAll();
	}

}
