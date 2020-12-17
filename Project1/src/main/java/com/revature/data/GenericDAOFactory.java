package com.revature.data;

public interface GenericDAOFactory<T> {
//	private Class<T> type;
//	
//	public GenericDAOFactory(Class<T> type) {
//		this.type = type;
//	}
	
	public GenericDAO<T> getDAO();
//	{
//		return new GenericHibernate<T>(this.type);
//	}
}
