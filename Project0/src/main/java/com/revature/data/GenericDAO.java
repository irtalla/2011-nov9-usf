package com.revature.data;

import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.exceptions.NonUniqueUsernameException;

public interface GenericDAO <T> {
	// CRUD operations (create, read, update, delete)
	public T add(T t) throws NullPointerException, NonUniqueUsernameException;
	public T getById(Integer id) throws NullPointerException;
	public Set<T> getAll();
	public void update(T t);
	public void delete(T t);
	
}
