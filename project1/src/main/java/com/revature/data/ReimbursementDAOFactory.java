package com.revature.data;

public class ReimbursementDAOFactory {
	public ReimbursementDAO getReimbursementDAO() {
		return new ReimbursementHibernate();
	}
}
