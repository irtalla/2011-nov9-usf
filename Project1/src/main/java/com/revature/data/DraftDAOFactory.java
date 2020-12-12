package com.revature.data;

public class DraftDAOFactory {
	public DraftDAO getDraftDao() {
		return new DraftHibernate();
	}
}
