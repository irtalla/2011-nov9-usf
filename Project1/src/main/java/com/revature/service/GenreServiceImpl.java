package com.revature.service;

import com.revature.beans.Genre;
import com.revature.data.GenreDAO;
import com.revature.data.GenrePostgres;

public class GenreServiceImpl implements GenreService {
	private GenreDAO gDao = new GenrePostgres();
	@Override
	public Integer addGenre(Genre g) {
		// TODO Auto-generated method stub
		return gDao.add(g).getId();
	}

	@Override
	public Genre getGenreById(Integer id) {
		// TODO Auto-generated method stub
		return gDao.getById(id);
	}

	@Override
	public void updateGenre(Genre g) {
		gDao.update(g);

	}

	@Override
	public void deleteGenre(Genre g) {
		gDao.delete(g);
	}

}
