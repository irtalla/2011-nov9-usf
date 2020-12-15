package servicespackage.dsa;

import data.DSA_DAOFactory;
import data.Draft_Status_Article_Dao;
import models.Draft_Status_Article;
import models.Draft_Status_Short;

public class DSA_ServiceImpl implements DSA_Service {
	private Draft_Status_Article_Dao dsadao;
	public DSA_ServiceImpl() {
		DSA_DAOFactory dsafactory = new DSA_DAOFactory();
		dsadao = dsafactory.getDao();
	}
	public Draft_Status_Article getById(Integer id) {
		// TODO Auto-generated method stub
		return dsadao.getById(id);
	}

	public Draft_Status_Article add(Draft_Status_Article e) {
		// TODO Auto-generated method stub
		return dsadao.add(e);
	}

	public void update(Draft_Status_Article e) {
		// TODO Auto-generated method stub
		dsadao.update(e);
	}

}
