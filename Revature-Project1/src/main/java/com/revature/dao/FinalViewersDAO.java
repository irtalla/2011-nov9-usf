package com.revature.dao;

import java.util.List;

import com.revature.beans.Editor;
import com.revature.beans.StoryPitch;

public interface FinalViewersDAO {
	public boolean addViewers(List<Editor> proofreaders, StoryPitch storyPitch);
	public boolean removeViewers(int approvalID);
	public List<Editor> retrieveAllViewersForASpecificPitch(int approvalID);
}
