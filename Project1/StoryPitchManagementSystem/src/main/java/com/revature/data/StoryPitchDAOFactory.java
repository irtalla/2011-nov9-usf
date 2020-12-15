package com.revature.data;

public class StoryPitchDAOFactory {
	
	public StoryPitchDAO getStoryPitchDAO() {        
		return new StoryPitchPostgreSQL();
	}

}
