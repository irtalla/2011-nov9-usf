package servicespackage.dso;

import models.Draft_Status_Other;

public interface DSO_Service {
	public Draft_Status_Other getById(Integer id);
	//public Draft_Status_Article getByUserId( Integer i);
	public Draft_Status_Other add(Draft_Status_Other e);
	public void update(Draft_Status_Other e);
}
