package servicespackage.dsa;

import models.Draft_Status_Article;
import models.Draft_Status_Short;

public interface DSA_Service {
	public Draft_Status_Article getById(Integer id);
	//public Draft_Status_Article getByUserId( Integer i);
	public Draft_Status_Article add(Draft_Status_Article e);
	public void update(Draft_Status_Article e);
}
