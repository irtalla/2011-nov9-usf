package data;

public class Approval_Other_DAOFactory {
	public ApprovalOtherDao getApprovalOtherDao() {
		return new Approval_OtherHibernate();
	}
}
