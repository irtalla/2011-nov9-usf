package data;

public class Pitch_Approval_DAOFactory {
	public Pitch_ApprovalDAO getDao() {
		return new Pitch_ApprovalHibernate();
	}
}
