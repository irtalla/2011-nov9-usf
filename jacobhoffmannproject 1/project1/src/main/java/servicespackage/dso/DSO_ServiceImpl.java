package servicespackage.dso;

import data.DSO_DAOFactory;
import data.Draft_Status_Other_Dao;
import models.Draft_Status_Other;

public class DSO_ServiceImpl implements DSO_Service{
	private Draft_Status_Other_Dao dsodao;
	public DSO_ServiceImpl() {
		DSO_DAOFactory dsofactory = new DSO_DAOFactory();
		dsodao = dsofactory.getDao();
	}
	public Draft_Status_Other getById(Integer id) {
		// TODO Auto-generated method stub
		return dsodao.getById(id);
	}

	public Draft_Status_Other add(Draft_Status_Other e) {
		// TODO Auto-generated method stub
		return dsodao.add(e);
	}

	public void update(Draft_Status_Other e) {
		// TODO Auto-generated method stub
		 dsodao.update(e);
	}

}
