package servicespackage.draft;

import java.util.Set;

import models.Draft;

public interface Draft_Service {
	public Draft getById(Integer id);
	public Set<Draft> getByAuthorId( Integer i);
	public Integer add(Draft e);
	public void update(Draft e);
}
