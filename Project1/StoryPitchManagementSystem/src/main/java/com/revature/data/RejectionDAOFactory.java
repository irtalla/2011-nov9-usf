package com.revature.data;

public class RejectionDAOFactory {

	public RejectionDAO getRejectionDAO() {
		return new RejectionPostgreSQL();
	}
}
