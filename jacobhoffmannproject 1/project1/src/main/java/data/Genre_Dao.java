package data;

import models.Draft_Status_Article;
import models.Draft_Status_Other;
import models.Genre;

public interface Genre_Dao {
	public Genre getById(Integer id);
	//public Draft_Status_Article getByUserId( Integer i);
	public Genre add(Genre e);
	public void update(Genre e);
}
