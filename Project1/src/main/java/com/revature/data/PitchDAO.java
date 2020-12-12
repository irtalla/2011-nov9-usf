package com.revature.data;

import java.util.Set;

import com.revature.beans.Pitch;

public interface PitchDAO extends GenericDAO<Pitch> {

	Set<Pitch> getPitchesByCommitteeId(Integer id);




}
