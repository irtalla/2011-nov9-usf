package com.revature.data;

public class GenreDAOFactory {

	public GenreDAO getGenreDao() {
		return new GenreHibernatePostgres();
	}
	
}
