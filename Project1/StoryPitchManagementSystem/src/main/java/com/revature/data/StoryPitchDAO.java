package com.revature.data;

import java.util.Set;

import com.revature.beans.StoryPitch;

public interface StoryPitchDAO extends GenericDAO<StoryPitch>{
	
	//create
	public StoryPitch add(StoryPitch sp);
	//read
	public Set<StoryPitch> getAll();
	public Set<StoryPitch> getByAuthorID(Integer id);
	public Set<StoryPitch> getByStatusID(Integer id);
	public Set<StoryPitch> getByPriorityAndGenreID(Integer pid, Integer gid);
	public Set<StoryPitch> getByStatusAndGenreID(Integer sid, Integer gid);
	public StoryPitch getByStoryPitchID(Integer id);
	//update
	public void update(StoryPitch sp);
	//delete
	public void delete(StoryPitch sp);

}
