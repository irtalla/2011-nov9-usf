package com.revature.data;

import java.util.Set;

import com.revature.beans.InfoRequest;

public interface InfoRequestDAO extends GenericDAO<InfoRequest>{
	
	//create
	public InfoRequest add(InfoRequest r);
	//read
	@Override
	public Set<InfoRequest> getAll();
	public Set<InfoRequest> getByRequestedID(Integer id);
	public InfoRequest getByRequestID(Integer id);
	//update
	@Override
	public void update(InfoRequest r);
	//delete
	@Override
	public void delete(InfoRequest r);

}
