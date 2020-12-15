package data;

public class Story_DAOFactory {
	public StoryDAO getDao() {
		return new StoryHibernate();
	}
}
