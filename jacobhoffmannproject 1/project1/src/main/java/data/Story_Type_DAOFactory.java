package data;

public class Story_Type_DAOFactory {
	public Story_TypeDAO getDao() {
		return new Story_TypeHibernate();
	}
}
