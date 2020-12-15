package servicespackage.pitch_status;

import java.util.Set;

import data.Pitch_Service_DAOFactory;
import data.Pitch_StatusDAO;
import models.Pitch_Status;

public class Pitch_Status_ServiceImpl implements Pitch_Status_Service{
	private Pitch_StatusDAO psdao;
	public Pitch_Status_ServiceImpl() {
		Pitch_Service_DAOFactory psfactory = new Pitch_Service_DAOFactory();
		psdao = psfactory.getDao();
	}
	public Pitch_Status getById(Integer id) {
		// TODO Auto-generated method stub
		return psdao.getById(id);
	}

	public Pitch_Status add(Pitch_Status e) {
		// TODO Auto-generated method stub
		return psdao.add(e);
	}

	public void update(Pitch_Status e) {
		// TODO Auto-generated method stub
		psdao.update(e);
	}
	@Override
	public Set<Pitch_Status> getByEditor(Integer id) {
		// TODO Auto-generated method stub
		return psdao.getByEditorId(id);
	}
	@Override
	public Set<Pitch_Status> getAll() {
		// TODO Auto-generated method stub
		return psdao.getAll();
	}
	@Override
	public Set<Pitch_Status> getByAuthorId(Integer id) {
		// TODO Auto-generated method stub
		return psdao.getByUserId(id);
	}

}
