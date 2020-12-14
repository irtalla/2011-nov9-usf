package com.revature.services;

import java.util.Set;

import com.revature.beans.Approval;
import com.revature.beans.ApprovalFile;
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

	@Override
	public Integer addApprovalFile(ApprovalFile e) {
		return DAOFactory.getApprovalFileDAO().add(e).getId();
	}

	@Override
	public Set<ApprovalFile> getApprovalFilesByFormId(Integer id) {
		return DAOFactory.getApprovalFileDAO().getApprovalFileByFormId(id);
	}

	@Override
	public void removeApprovalFile(ApprovalFile e) {
		DAOFactory.getApprovalFileDAO().delete(e);
		
	}

}
