package com.revature.dao;

import java.util.List;

import com.revature.beans.Editor;
import com.revature.beans.StoryPitch;

public interface ApprovalProofreadDAO {
	public boolean addProofreaders(List<Editor> proofreaders, StoryPitch storyPitch);
	public boolean removeProofreaders(int approvalID);
	public List<Editor> retrieveProofreadersForAPitch(int approvalID);
}
