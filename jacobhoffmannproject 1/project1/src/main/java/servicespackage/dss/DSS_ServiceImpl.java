package servicespackage.dss;

import data.DSS_DAOFactory;
import data.Draft_Status_Short_Dao;
import models.Draft_Status_Short;

public class DSS_ServiceImpl implements DSS_Service{
	private Draft_Status_Short_Dao dssdao;
	public DSS_ServiceImpl() {
		DSS_DAOFactory dssfactory = new DSS_DAOFactory();
	}
	public Draft_Status_Short getById(Integer id) {
		// TODO Auto-generated method stub
		return dssdao.getById(id);
	}

	public Draft_Status_Short add(Draft_Status_Short e) {
		// TODO Auto-generated method stub
		return dssdao.add(e);
	}

	public void update(Draft_Status_Short e) {
		// TODO Auto-generated method stub
		dssdao.update(e);
	}
	
}
