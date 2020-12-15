package com.trms.services;

import com.trms.beans.Activity;
import com.trms.data.ActivityDAO;
import com.trms.data.ActivityDAOFactory;

public class ActivityServiceImpl implements ActivityService {
	private ActivityDAO activityDao;
	
	public ActivityServiceImpl() {
		ActivityDAOFactory adf = new ActivityDAOFactory();
		activityDao = adf.getActivityDAO();
	}

	@Override
	public Activity addActivity(Activity a) {

		return activityDao.add(a);
	}

}
