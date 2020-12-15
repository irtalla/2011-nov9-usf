package servicespackage.Approval;

import data.ApprovalOtherDao;
import data.Approval_Other_DAOFactory;
import data.AuthorDao;
import data.Author_DAOFactory;
import models.Approval_Other;

public class Approval_Other_ServiceImpl implements Approval_Other_Service{
	private ApprovalOtherDao adao;
	public Approval_Other_ServiceImpl() {
		Approval_Other_DAOFactory afactory = new Approval_Other_DAOFactory();
		adao = afactory.getApprovalOtherDao();
	}
	@Override
	public Approval_Other getApprovalById(Integer id) {
		// TODO Auto-generated method stub
		return adao.getApprovalById(id);
	}

	@Override
	public Approval_Other getByEditorId(Integer i) {
		// TODO Auto-generated method stub
		return adao.getByEditorId(i);
	}

	@Override
	public Approval_Other add(Approval_Other e) {
		// TODO Auto-generated method stub
		return adao.add(e);
	}

	@Override
	public void updateApproval(Approval_Other e) {
		// TODO Auto-generated method stub
		adao.updateApproval(e);
	}

}
