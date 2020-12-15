package com.trms.services;

import java.util.List;

import com.trms.beans.Request;

public interface RequestService {
	public Integer addRequest(Request r);
	public Request getById(Integer id);
	public List<Request> getByPersonId(Integer id);
	public List<Request> getByManagerId(Integer id);
	public List<Request> getBydHeadId(Integer id);
	public boolean update (Request r);

    List<Request> getAll();
}
