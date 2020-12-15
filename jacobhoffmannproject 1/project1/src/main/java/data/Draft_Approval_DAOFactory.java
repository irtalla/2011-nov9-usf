package data;

public class Draft_Approval_DAOFactory {
	public Draft_Approval_Dao getDraftApprovalDao() {
		return new Draft_ApprovalHibernate();
	}
}
