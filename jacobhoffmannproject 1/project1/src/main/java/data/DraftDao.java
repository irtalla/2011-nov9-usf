package data;

import java.util.Set;

import models.Draft;
import models.Employee;

public interface DraftDao {
	public Draft getById(Integer id);
	public Set<Draft> getByAuthorId( Integer i);
	public Draft add(Draft e);
	public void update(Draft e);
}
