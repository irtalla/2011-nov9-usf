package dev.warrington.services;

import java.util.Set;

import dev.warrington.beans.Story;

public interface StoryService {

	public Set<Story> getMyStories(Integer id, Integer roleId);
	
}