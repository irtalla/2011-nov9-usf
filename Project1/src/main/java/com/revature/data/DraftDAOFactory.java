package com.revature.data;

import com.revature.beans.Draft;

public class DraftDAOFactory implements GenericDAOFactory<Draft>{
	@Override
	public DraftDAO getDAO() {
		return new DraftHibernate();
	}
}
