package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import com.revature.data.AdditionalFileDAOFactory;
import com.revature.data.GenreDAO;
import com.revature.data.GenreDAOFactory;
import com.revature.data.PitchDAO;
import com.revature.data.PitchDAOFactory;
import com.revature.data.PitchStageDAO;
import com.revature.data.PitchStageDAOFactory;
import com.revature.data.ReviewStatusDAO;
import com.revature.data.ReviewStatusDAOFactory;
import com.revature.data.StoryTypeDAO;
import com.revature.data.StoryTypeDAOFactory;
import com.revature.models.Genre;
import com.revature.models.Pitch;
import com.revature.models.PitchStage;
import com.revature.models.Priority;
import com.revature.models.ReviewStatus;
import com.revature.models.StoryType;
import com.revature.models.User;

public class PitchServiceImpl implements PitchService {
	private PitchDAO pitchDao;
	private GenreDAO genreDao;
	private StoryTypeDAO stDao;
	private PitchStageDAO psDao;
	private ReviewStatusDAO rsDao;
	
	public PitchServiceImpl() {
		PitchDAOFactory pitchFactory = new PitchDAOFactory();
		pitchDao = pitchFactory.getPitchDao();
		GenreDAOFactory genreFactory = new GenreDAOFactory();
		genreDao = genreFactory.getGenreDao();
		StoryTypeDAOFactory stFactory = new StoryTypeDAOFactory();
		stDao = stFactory.getStoryTypeDao();
		PitchStageDAOFactory psFactory = new PitchStageDAOFactory();
		psDao = psFactory.getPitchStageDao();
		ReviewStatusDAOFactory rsFactory = new ReviewStatusDAOFactory();
		rsDao = rsFactory.getReviewStatusDao();
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
	public Set<Pitch> getPitchesByGenre(Integer genre_id, Boolean withinGenre) {
		Genre genre = genreDao.getById(genre_id);
		
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
	public Set<Pitch> getPitchesByPriority(String label) {
		Priority priority = Priority.NORMAL;
		for (Priority p : Priority.values() ) {
			if (p.label == label) {
				priority = p;
			}
		}
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
	
	// Genre-related
	public Set<Genre> getAllGenre() {
		return genreDao.getAll();
	}
	
	// StoryType-related
	public Set<StoryType> getAllStoryType() {
		return stDao.getAll();
	}
	
	// PitchStage-related
	public Set<PitchStage> getAllPitchStage() {
		return psDao.getAll();
	}
	
	// ReviewStatus-related
	public Set<ReviewStatus> getAllReviewStatus() {
		return rsDao.getAll();
	}
	
	// Priority-related
	public Set<String> getPriorities() {
		Set<String> priorities = new HashSet<>();
		for (Priority p : Priority.values()) {
			priorities.add(p.label);
		}
		return priorities;
	}

}
