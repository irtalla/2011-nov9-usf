package com.revature.services;

import java.util.Set;

public interface GenericService<T> {
	//create:
	public Integer add(T t);
	
	// read:
	public T getById(Integer id);
	public Set<T> getAll();
	
	// update
	public void update(T t);
	
	// delete
	public void delete(T t);
}
