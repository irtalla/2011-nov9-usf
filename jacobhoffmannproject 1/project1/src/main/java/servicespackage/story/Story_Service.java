package servicespackage.story;

import java.util.Set;

import models.Story;

public interface Story_Service {
	public Story getById(Integer id);
	//public Draft_Status_Article getByUserId( Integer i);
	public Set<Story> getByUserId(Integer id);
	public Story add(Story e);
	public void update(Story e);
}
