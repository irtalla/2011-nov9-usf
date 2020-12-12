package com.cross.services;

import java.util.Set;

import com.cross.beans.Decision;
import com.revature.data.DecisionDAO;
import com.revature.data.DecisionHibernate;

public class DecisionServiceImpl implements DecisionService {
	
	private DecisionDAO decisionDAO; 
	
	public DecisionServiceImpl() {
		decisionDAO = new DecisionHibernate(); 
	}
	@Override
	public Decision add(Decision d) {
		return decisionDAO.add(d);
	}

	@Override
	public Set<Decision> getDecisionsByPitchId(Integer id) {
		return decisionDAO.getByPitchId(id);
	}

}
