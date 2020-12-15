package com.revature.data;

import java.util.Set;

import com.revature.beans.ChangeRequest;

//import com.revature.exceptions.NonUniqueUsernameException;

public interface GenericDAO <T> {
	// CRUD operations (create, read, update, delete)
	//public T add(T t) throws Exception;
	public Set<T> getAll();
	public void update(T t);
	public void delete(T t);
}
