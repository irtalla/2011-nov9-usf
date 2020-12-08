package JDBC;
import java.util.Set;

public interface Generics<T> {
	
	public T add(T t);
	public T getById(Integer id);
	public Set<T> getAll();
	public void update(T t);
	public void delete(T t);

}
