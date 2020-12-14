package com.revature.services;

import java.sql.Date;
import java.util.Set;

import com.revature.beans.Author;
import com.revature.beans.FurtherInfoRequests;
import com.revature.beans.StoryPitch;
import com.revature.beans.Work;

public interface AuthorFunctions {
	public StoryPitch addProposedWork(StoryPitch storyPitch);
	public Set<Work> getAllOfAuthorsWorks(Author author);
	public void addRequestedInfo(FurtherInfoRequests furtherInfoRequests);
	public void authorRemoveProposedWork(int userID, int workID);
}
