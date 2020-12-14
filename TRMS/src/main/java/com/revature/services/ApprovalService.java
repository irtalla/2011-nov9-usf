package com.revature.services;

import java.util.Set;

import com.revature.beans.Approval;
import com.revature.beans.ApprovalFile;

public interface ApprovalService {
	public Integer addApproval(Approval e);
	public Integer addApprovalFile(ApprovalFile e);
	// "read" methods
	public Approval getApprovalById(Integer id);
	public Set<Approval> getApprovals();
	public Set<ApprovalFile> getApprovalFilesByFormId(Integer id);
	// "update" methods
	public void updateApproval(Approval e);
	// "delete" methods
	public void removeApproval(Approval e);
	public void removeApprovalFile(ApprovalFile e);
}
