package dev.warrington.data;

import java.util.Set;

import dev.warrington.beans.Story;

public interface StoryDAO extends GenericDAO {
	public Set<Story> getMyStories(Integer id, Integer roleId);
	public void addStory(Story s);
	public void approve(Integer id);
	public void deny(Integer id);
	public void req(Integer id);
}