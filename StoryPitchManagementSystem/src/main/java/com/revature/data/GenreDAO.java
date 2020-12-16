package com.revature.data;

import java.util.Set;

import com.revature.beans.Genre;

public interface GenreDAO extends GenericDAO<Genre>{
	public Set<Genre> getAll();
}
