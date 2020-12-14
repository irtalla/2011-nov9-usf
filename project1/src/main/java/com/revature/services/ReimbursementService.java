package com.revature.services;

import java.util.Set;

import com.revature.beans.Individual;
import com.revature.beans.Reimbursement;

public interface ReimbursementService {
	// create
	public Integer addReimbursement(Reimbursement r) throws Exception;
	// read
	public Set<Reimbursement> getReimbursementById(Integer id);
	public Set<String> getAttachmentsById(Integer id);
	public Set<Reimbursement> getReimbursementByUsername(String username);
	// update
	public void updateReimbursement(Reimbursement r);
	// delete
	public void deleteReimbursement(Reimbursement r);
	public Set<Reimbursement> getAll();
	public Reimbursement getSingleReimbursementById(Integer id);
	public Reimbursement addMessageToReimbursement(Integer id, String message);
	public Integer approve(Integer id, Integer n);
}
