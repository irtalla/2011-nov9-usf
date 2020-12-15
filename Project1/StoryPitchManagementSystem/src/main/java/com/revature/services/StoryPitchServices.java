package com.revature.services;

import java.util.Set;

import com.revature.beans.StoryPitch;

public interface StoryPitchServices {

	// create
	public Integer addStoryPitch(StoryPitch sp);
	//read
	public StoryPitch getByStoryPitchID(Integer id);
	public Set<StoryPitch> getByAuthorID(Integer id);
	public Set<StoryPitch> getByGenreID(Integer id);
	public Set<StoryPitch> getByStatusID(Integer id);
	public Set<StoryPitch> getByPriorityAndGenreID(Integer pid, Integer gid);
	public Set<StoryPitch> getByStatusAndGenreID(Integer sid, Integer gid);
	public Set<StoryPitch> getAll();
	//update
	public void updateStoryPitch(StoryPitch sp);
	//delete
	public void deleteStoryPitch(StoryPitch sp);
	
}
