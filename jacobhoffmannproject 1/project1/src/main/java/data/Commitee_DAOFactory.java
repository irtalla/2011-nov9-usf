package data;

public class Commitee_DAOFactory {
	public CommiteeDao getCommiteeDao() {
		return new CommiteeHibernate();
	}
}
