package com.revature.data;

import java.util.List;

import com.revature.models.PitchStage;

public interface PitchStageDAO extends GenericDAO<PitchStage> {
	public List<PitchStage> getAllOrdered();
}
