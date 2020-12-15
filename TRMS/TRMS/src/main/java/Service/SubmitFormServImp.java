package Service;

import java.util.Set;

import Entity.SubmitForm;
import JDBC.SubmitFormDAO;
import JDBC.SubmitFormDAOFactory;

public class SubmitFormServImp implements SubmitFormServ {

private SubmitFormDAO submitFormDao;
	
	public SubmitFormServImp() {
		SubmitFormDAOFactory submitFormDaoFactory = new SubmitFormDAOFactory();
		submitFormDao = submitFormDaoFactory.getSubmitFormDAO();
	}
	
	@Override
	public SubmitForm add(SubmitForm sf) {
		// TODO Auto-generated method stub
		return submitFormDao.add(sf);
	}

	@Override
	public Set<SubmitForm> getAll() {
		// TODO Auto-generated method stub
		return submitFormDao.getAll();
	}
	
	@Override
	public Set<SubmitForm> getDS() {
		// TODO Auto-generated method stub
		return submitFormDao.getDS();
	}
	
	@Override
	public Set<SubmitForm> getDH() {
		// TODO Auto-generated method stub
		return submitFormDao.getDH();
	}
	
	@Override
	public Set<SubmitForm> getHY() {
		// TODO Auto-generated method stub
		return submitFormDao.getHY();
	}
	
	@Override
	public Set<SubmitForm> getPile() {
		// TODO Auto-generated method stub
		return submitFormDao.getPile();
	}

	@Override
	public SubmitForm getById(Integer emp_id) {
		// TODO Auto-generated method stub
		System.out.println("2");
		return submitFormDao.getById(emp_id);
	}

	@Override
	public void update(SubmitForm sf) {
		// TODO Auto-generated method stub
		System.out.println("5");
		submitFormDao.update(sf);
	}

}
