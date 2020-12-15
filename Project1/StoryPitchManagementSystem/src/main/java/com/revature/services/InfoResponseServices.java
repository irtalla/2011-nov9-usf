package com.revature.services;


import com.revature.beans.InfoResponse;
import java.util.Set;

public interface InfoResponseServices {
	
	// create
	public Integer addInfoResponse(InfoResponse cr);
	//read
	public InfoResponse getByResponseID(Integer id);
	public Set<InfoResponse> getByRespondedID(Integer id);
	public Set<InfoResponse> getAll();
	//update
	public void updateInfoResponse(InfoResponse r);
	//delete
	public void deleteInfoResponse(InfoResponse r);
	

}
