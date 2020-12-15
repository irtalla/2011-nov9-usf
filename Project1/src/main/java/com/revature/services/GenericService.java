package com.revature.services;

import java.util.Set;

public interface GenericService<T> {
	//create:
	public Integer add(T t);
	
	// read:
	public T getByIdEagerly(Integer id);
	public T getByIdLazily(Integer id);
	public Set<T> getAllEagerly();
	public Set<T> getAllLazily();
	// update
	public void update(T t);
	
	// delete
	public void delete(T t);
}
