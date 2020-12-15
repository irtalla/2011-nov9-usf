package servicespackage.request_info;

import data.Request_Info_DAOFactory;
import data.Request_Info_Dao;
import models.Request_Info;

public class Request_Info_ServiceImpl implements Request_Info_Service{
	private Request_Info_Dao ridao;
	public Request_Info_ServiceImpl() {
		Request_Info_DAOFactory rifactory = new Request_Info_DAOFactory();
		ridao = rifactory.getDao();
	}
	public Request_Info getById(Integer id) {
		// TODO Auto-generated method stub
		return ridao.getById(id);
	}

	public Request_Info add(Request_Info e) {
		// TODO Auto-generated method stub
		return ridao.add(e);
	}

	public void update(Request_Info e) {
		// TODO Auto-generated method stub
		ridao.update(e);
	}

}
