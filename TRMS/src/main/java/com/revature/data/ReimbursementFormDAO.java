package com.revature.data;

import java.util.Set;

import com.revature.beans.GradePresentationFile;
import com.revature.beans.GradingFormat;
import com.revature.beans.ReimbursementForm;
import com.revature.beans.Stage;
import com.revature.beans.Status;

public interface ReimbursementFormDAO extends GenericDAO<ReimbursementForm> {
	public Stage getStageById(Integer id);
	public Status getStatusById(Integer id);
	public GradingFormat getGradingFormatById(Integer id);
	public Set<GradingFormat> getAllGradingFormats();
	public Integer addPresentationFile(GradePresentationFile file);
	public GradePresentationFile getGradePresentationFileById(Integer id);
	public GradePresentationFile getGradePresentationFileByFormId(Integer id);

}
