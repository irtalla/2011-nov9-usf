package data;

public class DSO_DAOFactory {
	public Draft_Status_Other_Dao getDao() {
		return new DSOHibernate();
	}
}
