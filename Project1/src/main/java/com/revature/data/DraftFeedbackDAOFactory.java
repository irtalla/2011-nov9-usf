package com.revature.data;

import com.revature.beans.DraftFeedback;

public class DraftFeedbackDAOFactory implements GenericDAOFactory<DraftFeedback>{
	@Override
	public DraftFeedbackDAO getDAO() {
		return new DraftFeedbackHibernate();
	}
}
