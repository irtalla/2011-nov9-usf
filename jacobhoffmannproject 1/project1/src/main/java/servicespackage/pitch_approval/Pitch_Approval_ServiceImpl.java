package servicespackage.pitch_approval;

import java.util.Set;

import data.Pitch_ApprovalDAO;
import data.Pitch_Approval_DAOFactory;
import models.Pitch_Approval;

public class Pitch_Approval_ServiceImpl implements Pitch_Approval_Service{
	private Pitch_ApprovalDAO psdao;
	public Pitch_Approval_ServiceImpl() {
		Pitch_Approval_DAOFactory pafactory = new Pitch_Approval_DAOFactory();
		psdao = pafactory.getDao();
	}
	public Pitch_Approval getById(Integer id) {
		// TODO Auto-generated method stub
		return psdao.getById(id);
	}

	public Pitch_Approval add(Pitch_Approval e) {
		// TODO Auto-generated method stub
		return psdao.add(e);
	}

	public void update(Pitch_Approval e) {
		// TODO Auto-generated method stub
		psdao.update(e);
	}
	@Override
	public Set<Pitch_Approval> getByEditorId(Integer id) {
		// TODO Auto-generated method stub
		return psdao.getByEditorId(id);
	}


}
