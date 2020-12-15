package data;

import java.util.Set;

import models.Draft_Approval;

public interface Draft_Approval_Dao {
	public Draft_Approval getDAById(Integer id);
	//public Draft_Approval getByEditorId( Integer i);
	public Draft_Approval getById(Integer i);
	public Set<Draft_Approval> getByEditorId(Integer i);
	public Draft_Approval add(Draft_Approval e);
	public void updateDraft_Approval(Draft_Approval e);
}
