package servicespackage.dss;

import models.Draft_Status_Short;

public interface DSS_Service {
	public Draft_Status_Short getById(Integer id);
	//public Draft_Status_Article getByUserId( Integer i);
	public Draft_Status_Short add(Draft_Status_Short e);
	public void update(Draft_Status_Short e);
}
