package servicespackage.draft;

import java.util.Set;

import data.DraftDao;
import data.Draft_DAOFactory;
import models.Draft;

public class Draft_ServiceImpl implements Draft_Service{
	private DraftDao ddao;
	public Draft_ServiceImpl() {
		Draft_DAOFactory dfactory = new Draft_DAOFactory();
		ddao =dfactory.getDao();
	}
	public Draft getById(Integer id) {
		// TODO dd-generated method stub
		return ddao.getById(id);
	}

	public Set<Draft> getByAuthorId(Integer i) {
		// TODO Auto-generated method stub
		return ddao.getByAuthorId(i);
	}

	public Integer add(Draft e)  {
		// TODO Auto-generated method stub
		
		return ddao.add(e).getId();
	}

	public void update(Draft e) {
		// TODO Auto-generated method stub
		ddao.update(e);
	}

}
