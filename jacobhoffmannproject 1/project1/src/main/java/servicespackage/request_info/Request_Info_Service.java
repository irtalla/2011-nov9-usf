package servicespackage.request_info;

import models.Request_Info;

public interface Request_Info_Service {
	public Request_Info getById(Integer id);
	//public Draft_Status_Article getByUserId( Integer i);
	public Request_Info add(Request_Info e);
	public void update(Request_Info e);
}
