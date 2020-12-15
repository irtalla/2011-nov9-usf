package com.revature.services;


import com.revature.beans.GenreSpecialization;
import java.util.Set;

public interface GenreSpecializationServices {
	
	// create
	//public Integer addGenreSpecialization(GenreSpecialization gs);
	//read
	public GenreSpecialization getByGenreSpecializationID(Integer id);
	public Set<GenreSpecialization> getAll();
	public Set<GenreSpecialization> getByGenredID(Integer id);
	//update
	public void updateGenreSpecialization(GenreSpecialization gs);
	//delete
	public void deleteGenreSpecialization(GenreSpecialization gs);
	

}
