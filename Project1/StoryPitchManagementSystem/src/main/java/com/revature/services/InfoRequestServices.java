package com.revature.services;


import com.revature.beans.InfoRequest;
import java.util.Set;

public interface InfoRequestServices {
	
	// create
	public Integer addInfoRequest(InfoRequest cr);
	//read
	public Set<InfoRequest> getByRequestedID(Integer id);
	public Set<InfoRequest> getAll();
	public InfoRequest getByRequestID(Integer id);
	//update
	public void updateInfoRequest(InfoRequest cr);
	//delete
	public void deleteInfoRequest(InfoRequest cr);
	

}
