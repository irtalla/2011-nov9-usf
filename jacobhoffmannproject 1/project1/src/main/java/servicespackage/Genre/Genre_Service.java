package servicespackage.Genre;

import models.Genre;

public interface Genre_Service {
	public Genre getById(Integer id);
	//public Draft_Status_Article getByUserId( Integer i);
	public Genre add(Genre e);
	public void update(Genre e);
}
