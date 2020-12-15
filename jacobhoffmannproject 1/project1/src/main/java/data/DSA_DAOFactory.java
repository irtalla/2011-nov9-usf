package data;

public class DSA_DAOFactory {
	public Draft_Status_Article_Dao getDao() {
		return new DSAHibernate();
	}
}
