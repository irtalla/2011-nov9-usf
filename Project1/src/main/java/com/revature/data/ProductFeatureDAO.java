package com.revature.data;

import java.util.Set;

import com.cross.beans.Attachment;

public interface ProductFeatureDAO extends GenericDAO<Attachment> {
	public Set<Attachment> getFeaturesByProductId(Integer id);
}
