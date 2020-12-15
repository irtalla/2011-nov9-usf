package com.revature.data;

import java.util.Set;

public interface GenericDAO<T> {
	Integer add(T t);
	T getById(Integer id);
	Set<T> getAll();
	void update(T t);
	void delete(T t);
}
