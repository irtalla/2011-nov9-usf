package com.revature.data;

import java.util.Set;

import com.revature.beans.Feature;

public interface ProductFeatureDAO extends GenericDAO<Feature> {
	public Set<Feature> getFeaturesByProductId(Integer id);
}
