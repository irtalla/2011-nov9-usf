package com.revature.services;

import com.revature.beans.Individual;

public interface IndividualService {
	// create
	public Integer addIndividual(Individual p);
	// read
	public Individual getIndividualById(Integer id);
	public Individual getIndividualByUsername(String username);
	// update
	public void updateIndividual(Individual p);
	// delete
	public void deleteIndividual(Individual p);
}
