package com.revature.services;

import com.revature.beans.Genre;
import com.revature.beans.GenreCommittee;

public interface GenreCommitteeService extends GenericService<GenreCommittee>{

	GenreCommittee getByGenre(Genre genre);

}
