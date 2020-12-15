package com.revature.services;

import java.util.Set;

import com.revature.models.Requests;
import com.revature.models.User;

public interface RequestService {
	public Integer addRequest(Requests t) throws Exception;
	public Requests getRequestById(Integer id);
	public Set<Requests> getRequestByRequester(Integer id);
	public Set<Requests> getRequestByRequestee(Integer id);
	public Set<Requests> getAllRequests();
	public void updateRequests(Requests t) throws Exception;
	public void deleteRequests(Requests t);
	public Requests parseContext(String ctx);
}
