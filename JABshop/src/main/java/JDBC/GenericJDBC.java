package JDBC;

import java.util.Set;

public interface GenericJDBC <T>{

	public T add(T t) throws Exception;
	public T getById(Integer id);
	public Set<T> getAll();
	public void update(T t);
	public void remove(T t);
}
