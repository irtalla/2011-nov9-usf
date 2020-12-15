package servicespackage.commitee;

import java.util.Set;

import data.CommiteeDao;
import data.Commitee_DAOFactory;
import models.Commitee;

public class Commitee_ServiceImpl implements Commitee_Service{
	private CommiteeDao cdao;
	public Commitee_ServiceImpl() {
		Commitee_DAOFactory cfactory = new Commitee_DAOFactory();
		cdao =cfactory.getCommiteeDao();
	}
	public Commitee getCommiteeById(Integer id) {
		// TODO Auto-generated method stub
		return cdao.getCommiteeById(id);
	}

	public Set<Commitee> getByGenreID(Integer i) {
		// TODO Auto-generated method stub
		return cdao.getByGenreID(i);
	}

	public Set<Commitee> getByEditorId(Integer i) {
		// TODO Auto-generated method stub
		return cdao.getByEditorId(i);
	}

	public Commitee add(Commitee e) {
		// TODO Auto-generated method stub
		return cdao.add(e);
	}

	public void updateCommitee(Commitee e) {
		// TODO Auto-generated method stub
	cdao.updateCommitee(e);	
	}
	
	public Set<Commitee> getAll() {
		// TODO Auto-generated method stub
		return cdao.getAll();
	}

}
