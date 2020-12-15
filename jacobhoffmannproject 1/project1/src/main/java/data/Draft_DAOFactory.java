package data;

public class Draft_DAOFactory {
	public DraftDao getDao() {
		return new Draft_Hibernate();
	}
}
