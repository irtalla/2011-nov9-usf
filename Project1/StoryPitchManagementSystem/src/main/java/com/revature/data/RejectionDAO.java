package com.revature.data;

import java.util.Set;

import com.revature.beans.Rejection;

public interface RejectionDAO extends GenericDAO<Rejection>{
	
	//create
	public Rejection add(Rejection r);
	//read
	@Override
	public Set<Rejection> getAll();
	public Set<Rejection> getByRejectedID(Integer id);
	public Rejection getByRejectionID(Integer id);
	//update
	@Override
	public void update(Rejection r);
	//delete
	@Override
	public void delete(Rejection r);

}
