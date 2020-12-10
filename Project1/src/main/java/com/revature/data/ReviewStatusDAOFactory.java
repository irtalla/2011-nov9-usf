package com.revature.data;

public class ReviewStatusDAOFactory {
	public ReviewStatusDAO getReviewStatusDao() {
		return new ReviewStatusHibernatePostgres();
	}
}
