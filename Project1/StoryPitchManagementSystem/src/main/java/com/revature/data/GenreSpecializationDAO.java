package com.revature.data;

import java.util.Set;

import com.revature.beans.GenreSpecialization;

public interface GenreSpecializationDAO extends GenericDAO<GenreSpecialization>{
	
	//create
	//public GenreSpecialization add(GenreSpecialization gs);
	//read
	@Override
	public Set<GenreSpecialization> getAll();
	public Set<GenreSpecialization> getByGenreID(Integer id);
	public GenreSpecialization getByGenreSpecializationID(Integer id);
	//update
//	@Override
//	public void update(GenreSpecialization gs);
	//delete
	@Override
	public void delete(GenreSpecialization gs);

}
