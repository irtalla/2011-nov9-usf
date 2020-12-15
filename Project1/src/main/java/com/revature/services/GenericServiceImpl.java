package com.revature.services;

import java.util.Set;

import com.revature.data.GenericDAO;
import com.revature.data.GenericDAOFactory;

public abstract class GenericServiceImpl<T> implements GenericService<T> {

	protected GenericDAO<T> dao;
	//use this method in the specific implementations, not this.dao:
	abstract GenericDAO<T> getDao();
	
//	public GenericServiceImpl(Class<T> type) {
//		dao = new GenericDAOFactory<T>(type).getGenericDAO();
//	}
	
	public GenericServiceImpl(GenericDAOFactory<T> factory) {
		dao = factory.getDAO();
	}
	
//	public GenericServiceImpl(GenericDAO<T> specificDao) {
//		dao = specificDao;
//	}
	
	@Override
	public Integer add(T t) {
		return dao.add(t);
	}

	@Override
	public T getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public Set<T> getAll() {
		return dao.getAll();
	}

	@Override
	public void update(T t) {
		dao.update(t);
	}

	@Override
	public void delete(T t) {
		dao.delete(t);
	}

}
