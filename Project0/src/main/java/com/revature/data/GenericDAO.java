package com.revature.data;

import java.util.Set;

import com.revature.exceptions.NonUniqueUsernameException;

public interface GenericDAO <T> {
	// CRUD operations (create, read, update, delete)
	public T add(T t) throws Exception;
	public T getById(Integer id);
	public Set<T> getAll();
	public T update(T t) throws NonUniqueUsernameException;
	public void delete(T t);
}
