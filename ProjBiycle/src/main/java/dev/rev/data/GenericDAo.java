package dev.rev.data;

import java.util.Set;


public interface GenericDAo<T> {
	public T add(T t) throws Exception;
	public T getById(Integer id);
	public Set<T> getAll();
	public void update(T t);
	public void delete(T t);

}
