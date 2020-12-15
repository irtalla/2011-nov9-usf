package servicespackage.Genre;

import data.Genre_DAOFactory;
import data.Genre_Dao;
import models.Genre;

public class Genre_ServiceImpl implements Genre_Service{
	private Genre_Dao gdao;
	public Genre_ServiceImpl() {
	Genre_DAOFactory gfactory = new Genre_DAOFactory();
	}
	public Genre getById(Integer id) {
		// TODO Auto-generated method stub
		return gdao.getById(id);
	}

	public Genre add(Genre e) {
		// TODO Auto-generated method stub
		return gdao.add(e);
	}

	public void update(Genre e) {
		// TODO Auto-generated method stub
		gdao.update(e);
	}

}
