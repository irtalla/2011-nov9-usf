package com.revature.dao;

import java.util.Set;

import com.revature.beans.StoryPitch;

public interface StoryPitchDAO {
	public StoryPitch addStoryPitch(StoryPitch sp);
	public boolean rejectAPitch(StoryPitch sp);
	public boolean acceptAPitch(StoryPitch sp);
	public StoryPitch retrieveAPitch(int userID, int workID);
	public Set<StoryPitch> retrievePitchesByEditorApproval(int editorID);
}
