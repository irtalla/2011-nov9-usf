package data;

public class UsrDAOFactory {
	public UsrDAO getUsrDAO() {
		
		return new UsrHibernate();
	}
}
