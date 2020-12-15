package dev.warrington.services;

import java.util.Set;

import dev.warrington.beans.Story;
import dev.warrington.data.PersonPostgres;
import dev.warrington.data.StoryDAO;
import dev.warrington.data.StoryPostgres;

public class StoryServiceImpl implements StoryService {

	private StoryDAO storyDao;
	
	public StoryServiceImpl() {
		storyDao = new StoryPostgres();
	}
	
	@Override
	public Set<Story> getMyStories(Integer id, Integer roleId) {
		return storyDao.getMyStories(id, roleId);
	}

	@Override
	public void addStory(Story s) {
		storyDao.addStory(s);
	}

	@Override
	public void approve(Integer id) {
		storyDao.approve(id);
	}

	@Override
	public void deny(Integer id) {
		storyDao.deny(id);
	}

	@Override
	public void req(Integer id) {
		storyDao.req(id);
	}

}