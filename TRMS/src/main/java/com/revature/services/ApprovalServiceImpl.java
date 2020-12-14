package com.revature.services;

import java.util.Set;

import com.revature.beans.Approval;
import com.revature.data.DAOFactory;

public class ApprovalServiceImpl implements ApprovalService {

	@Override
	public Integer addApproval(Approval a) {
		return DAOFactory.getApprovalDAO().add(a).getId();
	}

	@Override
	public Approval getApprovalById(Integer id) {
		return DAOFactory.getApprovalDAO().getById(id);
	}

	@Override
	public Set<Approval> getApprovals() {
		return DAOFactory.getApprovalDAO().getAll();
	}

	@Override
	public void updateApproval(Approval a) {
		DAOFactory.getApprovalDAO().update(a);
		
	}

	@Override
	public void removeApproval(Approval a) {
		DAOFactory.getApprovalDAO().delete(a);
		
	}

}
