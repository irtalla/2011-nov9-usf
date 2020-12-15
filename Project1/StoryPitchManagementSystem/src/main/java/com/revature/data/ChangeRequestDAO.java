package com.revature.data;

import java.util.Set;

import com.revature.beans.ChangeRequest;

public interface ChangeRequestDAO extends GenericDAO<ChangeRequest>{

	//create
	public ChangeRequest add(ChangeRequest r);
	//read
	public Set<ChangeRequest> getAll();
	public Set<ChangeRequest> getByRequestedID(Integer id);
	public ChangeRequest getByRequestID(Integer id);
	//update
	@Override
	public void update(ChangeRequest r);
	//delete
	@Override
	public void delete(ChangeRequest r);
}
