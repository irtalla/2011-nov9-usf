package com.revature.data;

import java.util.Set;

import com.revature.beans.InfoResponse;

public interface InfoResponseDAO extends GenericDAO<InfoResponse>{
	
	//create
	public InfoResponse add(InfoResponse r);
	//read
	@Override
	public Set<InfoResponse> getAll();
	public Set<InfoResponse> getByRespondedID(Integer id);
	public Set<InfoResponse> getByRespondingID(Integer id);
	public InfoResponse getByResponseID(Integer id);
	//update
	@Override
	public void update(InfoResponse r);
	//delete
	@Override
	public void delete(InfoResponse r);

}
