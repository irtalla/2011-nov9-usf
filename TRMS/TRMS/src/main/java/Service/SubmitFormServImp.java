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
	public SubmitForm getByIds(Integer emp_id, Integer event_id) {
		// TODO Auto-generated method stub
		return submitFormDao.getByIds(emp_id, event_id);
	}

	@Override
	public void update(SubmitForm sf) {
		// TODO Auto-generated method stub
		submitFormDao.update(sf);
	}

}
