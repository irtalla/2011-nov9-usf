package dev.warrington.services;

import java.util.Set;

import dev.warrington.beans.Story;

public interface StoryService {

	public Set<Story> getMyStories(Integer id, Integer roleId);
	public void addStory(Story s);
	public void approve(Integer id);
	public void deny(Integer id);
	public void req(Integer id);
	
}