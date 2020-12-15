package com.trms.data;

import com.trms.beans.RequestType;

public interface RequestTypeDAO extends GenericDAO<RequestType> {
	public RequestType add(RequestType r);
	public RequestType getByName(String name);

}
