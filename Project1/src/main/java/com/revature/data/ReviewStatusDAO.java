package com.revature.data;

import java.util.List;

import com.revature.models.ReviewStatus;

public interface ReviewStatusDAO extends GenericDAO<ReviewStatus> {
	public List<ReviewStatus> getAllOrdered();
}
