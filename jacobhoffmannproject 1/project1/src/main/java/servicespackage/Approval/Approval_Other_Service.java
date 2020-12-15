package servicespackage.Approval;

import models.Approval_Other;

public interface Approval_Other_Service {
	public Approval_Other getApprovalById(Integer id);
	public Approval_Other getByEditorId( Integer i);
	public Approval_Other add(Approval_Other e);
	public void updateApproval(Approval_Other e);
}
