package com.revature.data;

import com.revature.models.AdditionalFile;

public interface AdditionalFileDAO extends GenericDAO<AdditionalFile> {
	public AdditionalFile getByPath(String path);
}
