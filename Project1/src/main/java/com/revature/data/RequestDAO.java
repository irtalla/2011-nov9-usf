package com.revature.data;

import java.util.Set;

import com.revature.models.Requests;
import com.revature.models.User;

public interface RequestDAO extends GenericDAO<Requests> {
	public Set<Requests> getByRequester(User t);
	public Set<Requests> getByRequestee(User t);
}
