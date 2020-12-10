package com.revature.services;

import java.util.Set;

import com.revature.models.Genre;
import com.revature.models.Pitch;
import com.revature.models.PitchStage;
import com.revature.models.Priority;
import com.revature.models.ReviewStatus;
import com.revature.models.StoryType;
import com.revature.models.User;

public interface PitchService {
	public Integer addPitch(Pitch t) throws Exception;
	public Pitch getPitchById(Integer id);
	public Set<Pitch> getPitchesByAuthor(User author);
	public Set<Pitch> getPitchesByGenre(Integer genreId, Boolean withinGenre);
	public Set<Pitch> getPitchesByStoryType(StoryType type);
	public Set<Pitch> getPitchesByPitchStage(PitchStage stage);
	public Set<Pitch> getPitchesByReviewStatus(ReviewStatus status);
	public Set<Pitch> getPitchesByPriority(String label);
	public Set<Pitch> getAllPitches();
	public void updatePitch(Pitch t) throws Exception;
	public void deletePitch(Pitch t);
	public Set<Genre> getAllGenre();
	public Set<StoryType> getAllStoryType();
	public Set<PitchStage> getAllPitchStage();
	public Set<ReviewStatus> getAllReviewStatus();
	public Set<String> getPriorities();
}
