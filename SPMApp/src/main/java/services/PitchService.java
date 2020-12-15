package services;

import beans.Usr;
import java.util.Set;

import beans.Approval;
import beans.Draft;
import beans.Pitch;

public interface PitchService {
	public void approvePitch(Usr u, Pitch p);
	public Pitch getPitchById(Integer Id);
	public Set<Pitch> getPitchesByAuthor(Integer Id);
	public Set<Pitch> getAll();
	public Set<Approval> getApprovals();
	// We need to implement approvePitch in serviceImpl such that we have an if check
	// on usr role Id which creates a new approval object for the respect id. 
	// Doable tonight.
	public void removePitch(Pitch pitch);
	public Set<Pitch> getPitches();
	public Set<Pitch> getPendingPitches();
	public void addPitch(Pitch pitch);
	public void updatePitch(Pitch pitch);
	public void addApproval(Approval approval);
	public void updateApproval(Approval approval);
	public Approval getApprovalById(Integer id);
	public void acceptDraft(Draft draft);
	public Set<Draft> getDrafts();
}
