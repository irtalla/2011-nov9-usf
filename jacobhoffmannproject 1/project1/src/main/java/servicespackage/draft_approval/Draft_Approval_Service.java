package servicespackage.draft_approval;

import java.util.Set;

import models.Draft_Approval;
import models.Pitch_Status;

public interface Draft_Approval_Service {
	//public Draft_Approval getByEditorId( Integer i);
	public Draft_Approval getById(Integer i);
	public Draft_Approval getByStoryId(Integer i);
	public Set<Draft_Approval> getByEditor(Integer id);
	public Draft_Approval add(Draft_Approval e);
	public void updateDraft_Approval(Draft_Approval e);
}
