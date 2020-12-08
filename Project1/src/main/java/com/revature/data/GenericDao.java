package com.revature.data;

import java.util.Set;

public interface GenericDao<T> {
	T add(T t);
	T getById(Integer id);
	Set<T> getAll();
	T update(T t);
	void delete(Integer id);
}
