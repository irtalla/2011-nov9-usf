package com.revature.data;

public class StoryTypeDAOFactory {

	public StoryTypeDAO getStoryTypeDao() {
		return new StoryTypeHibernatePostgres();
	}

}
