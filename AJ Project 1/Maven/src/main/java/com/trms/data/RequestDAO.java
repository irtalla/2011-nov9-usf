package com.trms.data;

import java.util.List;

import com.trms.beans.Request;

public interface RequestDAO extends GenericDAO<Request> {
	public Request add(Request r);

	public List<Request> getByRequestorId(Integer id);

    List<Request> getByManagerId(Integer id);

	List<Request> getBydHeadId(Integer id);
}
