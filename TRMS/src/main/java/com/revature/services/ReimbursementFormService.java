package com.revature.services;



import java.util.Set;

import com.revature.beans.GradePresentationFile;
import com.revature.beans.GradingFormat;
import com.revature.beans.ReimbursementChangeNotification;
import com.revature.beans.ReimbursementForm;
import com.revature.beans.Stage;
import com.revature.beans.Status;

public interface ReimbursementFormService {
	public Integer addReimbursementForm(ReimbursementForm e);
	public Integer addGradePresentationFile(GradePresentationFile f);
	public Integer addReimbursementChangeNotification(ReimbursementChangeNotification f);
	// "read" methods
	public ReimbursementForm getReimbursementFormById(Integer id);
	public Set<ReimbursementForm> getReimbursementForms();
	public Stage getStageById(Integer id);
	public Status getStatusById(Integer id);
	public GradingFormat getGradingFormatById(Integer id);
	public Set<GradingFormat> getGradingFormats();
	public GradePresentationFile getGradePresentionFileById(Integer id);
	public Set<GradePresentationFile> getGradePresentionFileByFormId(Integer id);
	public Set<ReimbursementChangeNotification> getReimbursementChangeNotificationByFormId(Integer id);
	
	// "update" methods
	public void updateReimbursementForm(ReimbursementForm e);
	public void updateReimbursementChangeNotification(ReimbursementChangeNotification f);
	// "delete" methods
	public void removeReimbursementForm(ReimbursementForm e);
}
