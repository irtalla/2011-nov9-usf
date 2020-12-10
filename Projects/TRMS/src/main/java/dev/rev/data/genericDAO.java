package dev.rev.data;

import java.util.List;
import java.util.Set;

public interface genericDAO<T> {
	public T add(T t) throws Exception;
	public T getById(Integer id);
	public List<T> getAll();
	public void update(T t);
	public void delete(T t);

	
}
