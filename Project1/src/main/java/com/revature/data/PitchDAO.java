package com.revature.data;

import java.util.List;
import java.util.Set;

import com.revature.models.AdditionalFile;
import com.revature.models.Genre;
import com.revature.models.Pitch;
import com.revature.models.PitchStage;
import com.revature.models.Priority;
import com.revature.models.ReviewStatus;
import com.revature.models.StoryType;
import com.revature.models.User;

public interface PitchDAO extends GenericDAO<Pitch> {
	public Set<Pitch> getByAuthor(User author);
	public Set<Pitch> getByGenre(Boolean withinGenre, List<Genre> genres);
	public Set<Pitch> getByStoryType(StoryType type);
	public Set<Pitch> getByPitchStage(PitchStage stage);
	public Set<Pitch> getByReviewStatus(ReviewStatus status);
	public Set<Pitch> getByPriority(Priority priority);
	public Pitch getByAdditionalFile(AdditionalFile file);
}
