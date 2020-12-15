package servicespackage.pitch_approval;

import java.util.Set;

import models.Pitch_Approval;

public interface Pitch_Approval_Service {
	public Pitch_Approval getById(Integer id);
	public Set<Pitch_Approval> getByEditorId(Integer id);
	//public Draft_Status_Article getByUserId( Integer i);
	public Pitch_Approval add(Pitch_Approval e);
	public void update(Pitch_Approval e);
}
