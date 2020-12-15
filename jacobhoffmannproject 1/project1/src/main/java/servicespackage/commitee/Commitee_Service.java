package servicespackage.commitee;

import java.util.Set;

import models.Commitee;

public interface Commitee_Service {
	public Commitee getCommiteeById(Integer id);
	public Set<Commitee> getByGenreID(Integer i);
	public Set<Commitee> getByEditorId( Integer i);
	public Set<Commitee> getAll();
	public Commitee add(Commitee e);
	public void updateCommitee(Commitee e);
}
