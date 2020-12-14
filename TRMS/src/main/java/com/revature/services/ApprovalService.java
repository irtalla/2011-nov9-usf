package com.revature.services;

import java.util.Set;

import com.revature.beans.Approval;

public interface ApprovalService {
	public Integer addApproval(Approval e);
	// "read" methods
	public Approval getApprovalById(Integer id);
	public Set<Approval> getApprovals();
	// "update" methods
	public void updateApproval(Approval e);
	// "delete" methods
	public void removeApproval(Approval e);
}
