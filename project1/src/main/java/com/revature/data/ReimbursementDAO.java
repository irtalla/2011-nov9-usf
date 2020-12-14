package com.revature.data;

import java.util.Set;

import com.revature.beans.Reimbursement;

public interface ReimbursementDAO extends GenericDAO<Reimbursement>{

	public Set<Reimbursement> getReimbursementsByUserId(Integer id);
	public Reimbursement updateReimbursement(Reimbursement r);
}