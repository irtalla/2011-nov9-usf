package spencer.revature.data;

import java.util.Set;

import spencer.revature.beans.Customer;

public interface GenericDAO <T> {
	// CRUD operations (create, read, update, delete)
	public T add(T t);
	public T getById(Integer id);
	public Set<T> getAll();
	public void update(T t);
	public void delete(T t);
}
