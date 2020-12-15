package data;

public class DSS_DAOFactory {
	public Draft_Status_Short_Dao getDao() {
		return new DSSHibernate();
	}
}
