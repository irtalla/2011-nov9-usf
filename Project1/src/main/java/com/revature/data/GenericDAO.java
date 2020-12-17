package com.revature.data;

import java.util.Set;

public interface GenericDAO<T> {
	Integer add(T t);
//	Set<T> getAllEagerly();
	Set<T> getAllEagerlyWhereOwnerIdIs(String ownerIdName, Integer ownerId);
	Set<T> getAllLazily();
	Set<T> getAllLazilyWhereOwnerIdIs(String ownerIdName, Integer ownerId);
	
	void update(T t);
	void delete(T t);
	T getByIdEagerly(Integer id);
	T getByIdLazily(Integer id);
	Set<T> getAllEagerly();
}
