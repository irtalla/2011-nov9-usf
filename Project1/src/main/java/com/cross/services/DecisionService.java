package com.cross.services;

import java.util.Set;

import com.cross.beans.Decision;

public interface DecisionService {
	
	public Decision add(Decision d); 
	public Set<Decision> getDecisionsByPitchId(Integer id);
}
