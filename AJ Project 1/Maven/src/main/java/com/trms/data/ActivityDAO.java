package com.trms.data;

import java.util.List;

import com.trms.beans.Activity;

public interface ActivityDAO extends GenericDAO<Activity> {
	public Activity add(Activity a);
	public List<Activity> getByReqId(Integer id);

}
