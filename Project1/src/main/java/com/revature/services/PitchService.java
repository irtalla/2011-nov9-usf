package com.revature.services;

import java.util.List;
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
	public Set<Pitch> getPitchesByAuthor(Integer id);
	public Set<Pitch> getPitchesByGenre(Boolean withinGenre, Integer ... genreIds);
	public Set<Pitch> getPitchesByStoryType(Integer typeId);
	public Set<Pitch> getPitchesByPitchStage(Integer stageId);
	public Set<Pitch> getPitchesByReviewStatus(Integer statusId);
	public Set<Pitch> getPitchesByPriority(String label);
	public Set<Pitch> getAllPitches();
	public void updatePitch(Pitch t) throws Exception;
	public void deletePitch(Pitch t);
	public List<Genre> getAllGenre();
	public List<StoryType> getAllStoryType();
	public List<PitchStage> getAllPitchStage();
	public List<ReviewStatus> getAllReviewStatus();
	public List<String> getPriorities();
	public Pitch parseContext(String ctx);
	public String updateFilePaths(String file);
	public Boolean checkShouldHold(Integer id);
	public void releaseHold(Pitch t);
	public Pitch advancePitch(Pitch t);
}
