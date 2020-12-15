package com.revature.data;

import java.util.Set;

import com.revature.beans.Approval;

public interface ApprovalDAO extends GenericDAO<Approval>{
	
	//create
	public Approval add(Approval a);
	//read
	@Override
	public Set<Approval> getAll();
	public Set<Approval> getByApprovedID(Integer id);
	public Approval getByApprovalID(Integer id);
	//update
	@Override
	public void update(Approval a);
	//delete
	@Override
	public void delete(Approval a);

}
