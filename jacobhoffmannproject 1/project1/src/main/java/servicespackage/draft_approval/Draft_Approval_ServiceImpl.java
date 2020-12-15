package servicespackage.draft_approval;

import java.util.Set;

import data.Draft_Approval_DAOFactory;
import data.Draft_Approval_Dao;
import models.Draft_Approval;

public class Draft_Approval_ServiceImpl implements Draft_Approval_Service{
	private Draft_Approval_Dao dadao;
	public Draft_Approval_ServiceImpl() {
		Draft_Approval_DAOFactory dafactory = new Draft_Approval_DAOFactory();
		dadao = dafactory.getDraftApprovalDao();
	}
	public Draft_Approval getByStoryId(Integer i) {
		// TODO Auto-generated method stub
		return dadao.getById(i);
	}

	public Draft_Approval add(Draft_Approval e) {
		// TODO Auto-generated method stub
		return dadao.add(e);
	}

	public void updateDraft_Approval(Draft_Approval e) {
		// TODO Auto-generated method stub
		dadao.updateDraft_Approval(e);
	}
	@Override
	public Draft_Approval getById(Integer i) {
		// TODO Auto-generated method stub
		return dadao.getDAById(i);
	}
	@Override
	public Set<Draft_Approval> getByEditor(Integer id) {
		// TODO Auto-generated method stub
		return dadao.getByEditorId(id);
	}

}
