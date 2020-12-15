package data;

public class Author_DAOFactory {
	public AuthorDao getApprovalOtherDao() {
		return new AuthorHibernate();
	}
}
