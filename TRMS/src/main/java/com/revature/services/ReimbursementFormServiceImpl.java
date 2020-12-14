package com.revature.services;

import java.util.Set;

import com.revature.beans.GradingFormat;
import com.revature.beans.ReimbursementForm;
import com.revature.beans.Stage;
import com.revature.beans.Status;
import com.revature.data.DAOFactory;
import com.revature.data.ReimbursementFormDAO;

public class ReimbursementFormServiceImpl implements ReimbursementFormService {
	private ReimbursementFormDAO dao = DAOFactory.getReimbursementFormDAO();
	@Override
	public Integer addReimbursementForm(ReimbursementForm f) {

		return dao.add(f).getId();
	}

	@Override
	public ReimbursementForm getReimbursementFormById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public Set<ReimbursementForm> getReimbursementForms() {
		return dao.getAll();
	}

	@Override
	public void updateReimbursementForm(ReimbursementForm f) {
		dao.update(f);

	}

	@Override
	public void removeReimbursementForm(ReimbursementForm f) {
		dao.delete(f);

	}

	@Override
	public Stage getStageById(Integer id) {
		return dao.getStageById(id);
	}

	@Override
	public Status getStatusById(Integer id) {
		return dao.getStatusById(id);
	}

	@Override
	public GradingFormat getGradingFormatById(Integer id) {
		return dao.getGradingFormatById(id);
	}

	@Override
	public Set<GradingFormat> getGradingFormats() {
		return dao.getAllGradingFormats();
	}

}
