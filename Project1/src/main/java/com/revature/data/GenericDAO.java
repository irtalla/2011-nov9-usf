package com.revature.data;

import java.util.Set;

import com.revature.exceptions.*;

public interface GenericDAO <T> {
	public Integer add(T t) throws Exception;
	public T getById(Integer id);
	public Set<T> getAll();
	public void update(T t) throws Exception;
	public void delete(T t);
}
