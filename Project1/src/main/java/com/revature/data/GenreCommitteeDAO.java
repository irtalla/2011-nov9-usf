package com.revature.data;

import com.revature.beans.Genre;
import com.revature.beans.GenreCommittee;
import com.revature.exceptions.InvalidCommitteeSizeException;

public interface GenreCommitteeDAO extends GenericDAO<GenreCommittee>{
	GenreCommittee addGenreCommittee(GenreCommittee gc) throws InvalidCommitteeSizeException;
	GenreCommittee updateGenreCommittee(GenreCommittee gc) throws InvalidCommitteeSizeException;
	GenreCommittee getByGenre(Genre g);
}
