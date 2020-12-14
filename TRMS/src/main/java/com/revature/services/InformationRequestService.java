package com.revature.services;

import java.util.Set;

import com.revature.beans.InformationRequest;

public interface InformationRequestService {

	public Integer addInformationRequest(InformationRequest e);
	// "read" methods
	public InformationRequest getInformationRequestById(Integer id);
	public Set<InformationRequest> getInformationRequests();
	// "update" methods
	public void updateInformationRequest(InformationRequest e);
	// "delete" methods
	public void removeInformationRequest(InformationRequest e);
}
