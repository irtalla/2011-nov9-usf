package data;

public class User_DAOFactory {
	public User_Dao getUserDao() {
		return new UserHibernate();
	}
}
