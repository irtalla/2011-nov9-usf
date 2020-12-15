package data;

import java.util.Set;

import models.Request_Info;
import models.Story;

public interface StoryDAO {
	public Story getById(Integer id);
	public Set<Story> getByUserId( Integer i);
	public Story add(Story e);
	public void update(Story e);
}
