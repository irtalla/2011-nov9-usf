package com.revature.services;

import java.util.Set;

import com.revature.data.PitchDAO;
import com.revature.data.PitchDAOFactory;
import com.revature.models.Genre;
import com.revature.models.Pitch;
import com.revature.models.PitchStage;
import com.revature.models.Priority;
import com.revature.models.ReviewStatus;
import com.revature.models.StoryType;
import com.revature.models.User;

public class PitchServiceImpl implements PitchService {
	private PitchDAO pitchDao;
	
	public PitchServiceImpl() {
		PitchDAOFactory pitchFactory = new PitchDAOFactory();
		pitchDao = pitchFactory.getPitchDao();
	}
	
	
	@Override
	public Integer addPitch(Pitch t) throws Exception {
		return pitchDao.add(t);
	}

	@Override
	public Pitch getPitchById(Integer id) {
		return pitchDao.getById(id);
	}

	@Override
	public Set<Pitch> getPitchesByAuthor(User author) {
		return pitchDao.getByAuthor(author);
	}

	@Override
	public Set<Pitch> getPitchesByGenre(Genre genre, Boolean withinGenre) {
		return pitchDao.getByGenre(genre, withinGenre);
	}

	@Override
	public Set<Pitch> getPitchesByStoryType(StoryType type) {
		return pitchDao.getByStoryType(type);
	}

	@Override
	public Set<Pitch> getPitchesByPitchStage(PitchStage stage) {
		return pitchDao.getByPitchStage(stage);
	}

	@Override
	public Set<Pitch> getPitchesByReviewStatus(ReviewStatus status) {
		return pitchDao.getByReviewStatus(status);
	}

	@Override
	public Set<Pitch> getPitchesByPriority(Priority priority) {
		return pitchDao.getByPriority(priority);
	}

	@Override
	public Set<Pitch> getAllPitches() {
		return pitchDao.getAll();
	}

	@Override
	public void updatePitch(Pitch t) throws Exception {
		pitchDao.update(t);
	}

	@Override
	public void deletePitch(Pitch t) {
		pitchDao.delete(t);
	}

}
