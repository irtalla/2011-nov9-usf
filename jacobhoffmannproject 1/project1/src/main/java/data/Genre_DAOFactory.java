package data;

public class Genre_DAOFactory {
	public Genre_Dao getDao() {
		return new GenreHibernate();
	}
}
