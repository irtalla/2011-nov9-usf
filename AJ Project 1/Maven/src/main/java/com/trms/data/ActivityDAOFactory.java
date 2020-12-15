package com.trms.data;

public class ActivityDAOFactory {
	public ActivityDAO getActivityDAO() {
		return new ActivityHibernate();
	}
}
