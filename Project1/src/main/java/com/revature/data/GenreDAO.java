package com.revature.data;

import java.util.List;

import com.revature.models.Genre;

public interface GenreDAO extends GenericDAO<Genre> {
	public List<Genre> getAllOrdered();
}
