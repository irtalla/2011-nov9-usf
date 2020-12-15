package com.revature.data;

import java.util.Set;

public interface GenericDAO<T> {
	// create
	public T add(T t) throws Exception;
	// read
	public T getById(Integer i);
	public Set<T> getAll();
	// update
	public void update(T t);
	// delete
	public void delete(T t);
}
