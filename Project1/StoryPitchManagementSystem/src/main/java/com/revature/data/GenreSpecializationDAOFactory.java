package com.revature.data;

public class GenreSpecializationDAOFactory {

	public GenreSpecializationDAO getGenreSpecializationDAO() {
		return new GenreSpecializationPostgreSQL();
	}
}
