package com.revature.services;

import com.revature.beans.Rejection;
import java.util.Set;

public interface RejectionServices {
	
	// create
	public Integer addRejection(Rejection a);
	//read
	public Rejection getByRejectionID(Integer id);
	public Set<Rejection> getByRejectedID(Integer id);
	public Set<Rejection> getAll();
	//update
	public void updateRejection(Rejection r);
	//delete
	public void deleteRejection(Rejection r);
	

}
