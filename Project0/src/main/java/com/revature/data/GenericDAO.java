package com.revature.data;

import java.util.Set;

public interface GenericDAO <T> {
	// Create
	public T add(T t) throws Exception;
	
	// Read
	public T getById(Integer id);
	public Set<T> getAll();
	
	// Update
	public void update(T t);
	
	// Delete
	public void delete(T t);
}
