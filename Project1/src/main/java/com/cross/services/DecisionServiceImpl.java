package com.cross.services;

import java.util.Set;

import com.cross.beans.Decision;
import com.cross.data.DecisionDAO;
import com.cross.data.DecisionHibernate;
import com.cross.exceptions.InvalidGeneralEditorException;

public class DecisionServiceImpl implements DecisionService {
	
	private DecisionDAO decisionDAO; 
	
	public DecisionServiceImpl() {
		decisionDAO = new DecisionHibernate(); 
	}
	@Override
	public Decision add(Decision d) {
		try {
			return decisionDAO.add(d);
		} catch (InvalidGeneralEditorException e) {
			e.printStackTrace();
			return null; 
		}
	}

	@Override
	public Set<Decision> getDecisionsByPitchId(Integer id) {
		return decisionDAO.getByPitchId(id);
	}

}
