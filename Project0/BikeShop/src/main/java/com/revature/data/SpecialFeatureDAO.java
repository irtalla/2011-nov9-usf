package com.revature.data;

import java.util.Set;

import com.revature.beans.SpecialFeature;

public interface SpecialFeatureDAO extends GenericDAO <SpecialFeature> {
	public SpecialFeature add(SpecialFeature sf);
	public Set<SpecialFeature> getAvailableSpecialFeatures();

}
