package com.revature.data;

public class DraftFeedbackDAOFactory {
	public DraftFeedbackDAO getDraftFeedbackDAO() {
		return new DraftFeedbackHibernate();
	}
}
