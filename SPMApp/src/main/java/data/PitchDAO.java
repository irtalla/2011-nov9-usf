package data;

import java.util.Set;

import beans.Usr;
import beans.Approval;
import beans.Draft;
import beans.Pitch;

public interface PitchDAO extends GenericDAO <Pitch> {
	public Pitch add(Pitch p);
	public Set<Pitch> getPendingPitches();
	public <T> void approvePitch(Usr u, Pitch p);
	public Set<Pitch> getPitchesByAuthor(Integer id);
	public Approval add(Approval approval);
	public Set<Approval> getApprovals();
	public void update(Approval approval);
	public Approval getApprovalById(Integer id);
	public Draft acceptDraft(Draft draft);
	public Set<Draft> getDrafts();
}
