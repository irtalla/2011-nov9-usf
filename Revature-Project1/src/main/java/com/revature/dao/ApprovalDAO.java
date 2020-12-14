package com.revature.dao;

import com.revature.beans.StoryPitch;

public interface ApprovalDAO {
	public void rejectAPitch(StoryPitch sp);
	public void acceptAPitch(StoryPitch sp);
}
