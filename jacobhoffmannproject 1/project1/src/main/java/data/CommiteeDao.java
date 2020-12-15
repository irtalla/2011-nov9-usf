package data;

import java.util.Set;

import models.Commitee;
import models.Employee;

public interface CommiteeDao {
	public Commitee getCommiteeById(Integer id);
	public Set<Commitee> getByGenreID(Integer i);
	public Set<Commitee> getByEditorId( Integer i);
	public Set<Commitee> getAll();
	public Commitee add(Commitee e);
	public void updateCommitee(Commitee e);
}
