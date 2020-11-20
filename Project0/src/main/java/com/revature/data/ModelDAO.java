package com.revature.data;

import com.revature.beans.Model;

public interface ModelDAO extends GenericDAO<Model> {
	public Model add(Model m);

}
