package com.revature.data;

public class ApprovalDAOFactory {

	public ApprovalDAO getApprovalDAO() {
		return new ApprovalPostgreSQL();
	}
}
