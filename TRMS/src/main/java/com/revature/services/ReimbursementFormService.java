package com.revature.services;

import java.util.Set;

import com.revature.beans.GradingFormat;
import com.revature.beans.ReimbursementForm;
import com.revature.beans.Stage;
import com.revature.beans.Status;

public interface ReimbursementFormService {
	public Integer addReimbursementForm(ReimbursementForm e);
	// "read" methods
	public ReimbursementForm getReimbursementFormById(Integer id);
	public Set<ReimbursementForm> getReimbursementForms();
	public Stage getStageById(Integer id);
	public Status getStatusById(Integer id);
	public GradingFormat getGradingFormatById(Integer id);
	public Set<GradingFormat> getGradingFormats();
	// "update" methods
	public void updateReimbursementForm(ReimbursementForm e);
	// "delete" methods
	public void removeReimbursementForm(ReimbursementForm e);
}
