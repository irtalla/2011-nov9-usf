package com.revature.services;

import java.util.Set;

import com.revature.beans.FurtherInfoRequests;
import com.revature.beans.StoryPitch;

public interface EditorFunctions {
	public void rejectAPitch(StoryPitch rejectedStoryPitch);
	public void acceptAPitch(StoryPitch acceptedStoryPitch);
	public Set<FurtherInfoRequests> getRequestedInfo(int approvalID, int approvalStage);
	public Set<StoryPitch> getPitchesByEditorApproval(int editorID);
}
