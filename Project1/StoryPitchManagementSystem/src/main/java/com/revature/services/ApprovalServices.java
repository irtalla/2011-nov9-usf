package com.revature.services;


import com.revature.beans.Approval;
import java.util.Set;

public interface ApprovalServices {
	
	// create
	public Integer addApproval(Approval a);
	//read
	public Approval getByApprovalID(Integer id);
	public Set<Approval> getByApprovedID(Integer id);
	public Set<Approval> getAll();
	//update
	public void updateApproval(Approval r);
	//delete
	public void deleteApproval(Approval r);
	

}
