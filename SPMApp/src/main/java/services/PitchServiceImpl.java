package services;

import java.util.Set;

import beans.Approval;
import beans.Draft;
import beans.Pitch;
import beans.Usr;
import data.PitchDAO;
import data.PitchDAOFactory;

public class PitchServiceImpl implements PitchService {
	private PitchDAO pitchDao;
	
	public PitchServiceImpl() {
		PitchDAOFactory pitchDaoFactory = new PitchDAOFactory();
		pitchDao = pitchDaoFactory.getPitchDAO();
	}
	@Override
	public void approvePitch(Usr u, Pitch p) {
		pitchDao.approvePitch(u, p);
	}

	@Override
	public Pitch getPitchById(Integer Id) {
		return pitchDao.getById(Id);
	}

	@Override
	public Set<Pitch> getPitchesByAuthor(Integer Id) {
		return pitchDao.getPitchesByAuthor(Id);
	}

	@Override
	public Set<Pitch> getAll() {
		return pitchDao.getAll();
	}

	@Override
	public Set<Approval> getApprovals() {
		return pitchDao.getApprovals();
	}

	@Override
	public void removePitch(Pitch pitch) {
		pitchDao.delete(pitch);
		
	}

	@Override
	public Set<Pitch> getPitches() {
		return pitchDao.getAll();
	}

	@Override
	public Set<Pitch> getPendingPitches(){
		return pitchDao.getPendingPitches();
	}

	@Override
	public void addPitch(Pitch pitch) {
		pitchDao.add(pitch);
		
	}

	@Override
	public void updatePitch(Pitch pitch) {
		pitchDao.update(pitch);
		
	}
	@Override
	public void addApproval(Approval approval) {
		pitchDao.add(approval);
		
	}
	@Override
	public void updateApproval(Approval approval) {
		pitchDao.update(approval);
		
	}
	@Override
	public Approval getApprovalById(Integer id) {
		return pitchDao.getApprovalById(id);
	}
	@Override
	public void acceptDraft(Draft draft) {
		pitchDao.acceptDraft(draft);
		
	}
	@Override
	public Set<Draft> getDrafts() {
		return pitchDao.getDrafts();
	}

}
