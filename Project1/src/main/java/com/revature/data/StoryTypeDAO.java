package com.revature.data;

import java.util.List;

import com.revature.models.StoryType;

public interface StoryTypeDAO extends GenericDAO<StoryType> {
	public List<StoryType> getAllOrdered();
}
