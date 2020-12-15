package data;

public class Request_Info_DAOFactory {
	public Request_Info_Dao getDao() {
		return new Request_InfoHibernate();
	}
}
