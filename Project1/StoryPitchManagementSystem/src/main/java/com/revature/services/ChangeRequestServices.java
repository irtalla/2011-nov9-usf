package com.revature.services;


import com.revature.beans.ChangeRequest;
import java.util.Set;

public interface ChangeRequestServices {
	
	// create
	public Integer addChangeRequest(ChangeRequest cr);
	//read
	public Set<ChangeRequest> getAll();
	public Set<ChangeRequest> getByAuthorID(Integer id);
	public ChangeRequest getByRequestID(Integer id);
	//update
	public void updateChangeRequest(ChangeRequest cr);
	//delete
	public void deleteChangeRequest(ChangeRequest cr);
	

}
